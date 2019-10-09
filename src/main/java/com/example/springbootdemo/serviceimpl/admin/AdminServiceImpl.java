package com.example.springbootdemo.serviceimpl.admin;

import com.example.springbootdemo.mapper.DEPOSITSMapper;
import com.example.springbootdemo.mapper.POINTSMapper;
import com.example.springbootdemo.mapper.PURCHASEMapper;
import com.example.springbootdemo.mapper.admin.hui.ADMINSMapper;
import com.example.springbootdemo.mapper.hui.*;
import com.example.springbootdemo.model.POINTS;
import com.example.springbootdemo.model.admin.hui.ADMINS;
import com.example.springbootdemo.model.hui.AUDITIMAGES;
import com.example.springbootdemo.model.hui.IMAGES;
import com.example.springbootdemo.model.hui.TYPES;
import com.example.springbootdemo.model.hui.USERS;
import com.example.springbootdemo.response.ResponseBo;
import com.example.springbootdemo.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Resource
    ADMINSMapper adminsMapper;
    @Resource
    USERSMapper usersMapper;
    @Resource
    IMAGESMapper imagesMapper;
    @Resource
    TYPESMapper typesMapper;
    @Resource
    AUDITIMAGESMapper auditimagesMapper;
    @Resource
    DEPOSITSMapper depositsMapper;
    @Resource
    POINTSMapper pointsMapper;
    @Resource
    COLLECTSMapper collectsMapper;
    @Resource
    THUMBMapper thumbMapper;
    @Resource
    PURCHASEMapper purchaseMapper;

    @Override
    public ResponseBo login(String name, String password) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        try {
            ADMINS admins = adminsMapper.selectByName(name);
            if (admins==null){
                responseBo.setResMsg("账号不存在");
                return responseBo;
            }
            if (!admins.getPassword().equals(password)){
                responseBo.setResMsg("密码错误");
                return responseBo;
            }
            responseBo.setResMsg("登录成功");
            responseBo.setResult(admins);
        }catch (Exception e){
            responseBo.setResMsg("登录失败");
            log.error("错误"+e);
            return responseBo;
        }
        return responseBo;
    }

    @Override
    public ResponseBo getUsers() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        try {
            List result = usersMapper.selectAll();
            if (result==null){
                responseBo.setResMsg("没有记录");
                return responseBo;
            }
            responseBo.setResMsg("查询成功");
            responseBo.setResult(result);
        }
        catch (Exception e){
            responseBo.setResMsg("查询出错");
            return responseBo;
        }
        return responseBo;
    }

    @Override
    public ResponseBo deleteUserById(int userid) throws Exception {
        ResponseBo responseBo= new ResponseBo();
        int res=usersMapper.deleteByPrimaryKey(userid);
        if (res==0){
            responseBo.setResMsg("删除失败");
            return responseBo;
        }
        responseBo.setResMsg("删除成功");
        return responseBo;
    }

    @Override
    public ResponseBo getBanUsers() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=usersMapper.selectBanUsers();
        if (result==null){
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo banUser(int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        USERS users=new USERS();
        users.setId(userid);
        users.setBan((short)1);
        int res=usersMapper.updateByPrimaryKeySelective(users);
        if (res==0){
            responseBo.setResMsg("封禁失败");
            return responseBo;
        }
        responseBo.setResMsg("封禁成功");
        return responseBo;
    }

    @Override
    public ResponseBo unbanUser(int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        USERS users=new USERS();
        users.setId(userid);
        users.setBan((short)0);
        int res=usersMapper.updateByPrimaryKeySelective(users);
        if (res==0){
            responseBo.setResMsg("解封失败");
            return responseBo;
        }
        responseBo.setResMsg("解封成功");
        return responseBo;
    }

    @Override
    public ResponseBo update(HttpServletRequest request, MultipartFile file, String name,int adminid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        ADMINS admins=adminsMapper.selectByPrimaryKey(adminid);

        String fileNames="";
        String newName="defaultPic.jpg";



        if (!admins.getPic().equals("defaultPic.jpg")){
            File oriPic=new File("D:/dhcProject/springbootdemo/images/"+admins.getPic());
            log.info("删除原头像");
            oriPic.delete();
        }

        if (file!=null){
            fileNames = file.getOriginalFilename();//这个fileNames是上传的文件名（不是地址）
            int split = fileNames.lastIndexOf(".");
            String suffix = fileNames.substring(split+1,fileNames.length());//文件后缀，用于判断上传的文件是否是合法的
            //判断文件类型，因为我这边是图片，所以只设置三种合法格式
            if(suffix.equals("jpg") || suffix.equals("png") || suffix.equals("gif")) {
                //正确的类型，保存文件
                byte[] bytes = file.getBytes();
                newName = new Date().getTime() + "."+suffix;
                File savedFile = new File("D:/dhcProject/springbootdemo/images/"+newName);//这里的路径根据自己的要求进行设置，有关路径配置的问题参考文章下半内容
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
                stream.write(bytes);
                stream.close();
                System.out.println("图片上传完毕，存储地址为："+savedFile.getAbsolutePath());
            }else {
                responseBo.setResMsg("图片类型不支持");
                return responseBo;
            }}
        admins.setPic(newName);
        if (name.equals(""))
            name=admins.getName();
        admins.setName(name);
        int res=adminsMapper.updateByPrimaryKeySelective(admins);
        responseBo.setResult(admins);
        if (res==1)
            responseBo.setResMsg("上传成功");
        else
            responseBo.setResMsg("上传失败");
        return responseBo;
    }

    @Override
    public ResponseBo deleteImgById(int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=imagesMapper.deleteByPrimaryKey(imgid);
        collectsMapper.deleteByImg(imgid);
        thumbMapper.deleteByImg(imgid);
        purchaseMapper.deleteByImg(imgid);
        if (res==0){
            responseBo.setResMsg("删除失败");
            return responseBo;
        }
        responseBo.setResMsg("删除成功");
        return responseBo;
    }

    @Override
    public ResponseBo addType(String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        TYPES types=new TYPES();
        types.setName(name);
        int res=typesMapper.insert(types);
        if (res==1){
            responseBo.setResMsg("添加成功");
            return responseBo;
        }
        responseBo.setResMsg("添加失败");
        return responseBo;

    }

    @Override
    public ResponseBo getTypes() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=typesMapper.selectAll();
        if (result==null){
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo getTypeByid(int typeid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        TYPES types=typesMapper.selectByPrimaryKey(typeid);
        if (types==null){
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(types);
        return responseBo;
    }

    @Override
    public ResponseBo updateType(int typeid,String typename) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=0;
        TYPES newtypes=new TYPES();
        newtypes.setId(typeid);
        newtypes.setName(typename);
        res=typesMapper.updateByPrimaryKey(newtypes);
        if (res==0){
            responseBo.setResMsg("更新失败");
            return responseBo;
        }
        responseBo.setResMsg("更新成功");
        return responseBo;
    }

    @Override
    public ResponseBo deleteType(int typeid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=0;

        List result=imagesMapper.selectByType(typeid);
        if (result!=null){
            responseBo.setResMsg("删除失败，该类型还有图片");
            return responseBo;
        }
        res=typesMapper.deleteByPrimaryKey(typeid);
        if (res==0){
            responseBo.setResMsg("删除失败");
            return responseBo;
        }
        responseBo.setResMsg("删除成功");
        return responseBo;
    }

    @Override
    public ResponseBo getAuditImgs() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=auditimagesMapper.selectAll();
        if (result==null){
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo auditImg(int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res1,res2=0;
        AUDITIMAGES auditimages=auditimagesMapper.selectByPrimaryKey(imgid);
        IMAGES images=new IMAGES();
        images.setTypeid(auditimages.getTypeid());
        images.setUserid(auditimages.getUserid());
        images.setIntro(auditimages.getIntro());
        images.setTitle(auditimages.getTitle());
        images.setImgname(auditimages.getImgname());
        res1=auditimagesMapper.deleteByPrimaryKey(imgid);
        res2=imagesMapper.insert(images);
        if (res1==0||res2==0){
            responseBo.setResMsg("审核失败");
            return responseBo;
        }
        responseBo.setResMsg("审核成功");
        return responseBo;
    }

    @Override
    public ResponseBo unauditImg(int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res1=0;
        AUDITIMAGES auditimages=auditimagesMapper.selectByPrimaryKey(imgid);

        String path = this.getClass().getResource("/").getPath().replace("%20", " ");
        String [] strs=path.split("/");
        String paths=strs[1]+"/"+strs[2]+"/"+strs[3];

        File oriPic=new File(paths+"/images/"+auditimages.getImgname());
        log.info("删除待审核图片");
        oriPic.delete();

        res1=auditimagesMapper.deleteByPrimaryKey(imgid);
        if (res1==0){
            responseBo.setResMsg("审核失败");
            return responseBo;
        }
        responseBo.setResMsg("审核成功");
        return responseBo;
    }

    @Override
    public ResponseBo getDeposits() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List list=depositsMapper.selectAll();
        if (list==null){
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        responseBo.setResMsg("获取成功");
        responseBo.setResult(list);
        return responseBo;
    }

    @Override
    public ResponseBo addPoint(POINTS points) throws Exception {
        int id=points.getId();//deposits表id
        ResponseBo responseBo=new ResponseBo();
        POINTS selPoint=pointsMapper.selectByUid(points.getUid());
        points.setId(selPoint.getId());
        points.setPoint(selPoint.getPoint()+points.getPoint());
        int res=pointsMapper.updateByPrimaryKey(points);
        depositsMapper.deleteByPrimaryKey(id);
        if (res==0){
            responseBo.setResMsg("添加失败");
            return responseBo;
        }
        responseBo.setResMsg("添加成功");
        return responseBo;
    }

}
