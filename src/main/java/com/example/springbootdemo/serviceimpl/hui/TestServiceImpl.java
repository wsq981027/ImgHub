package com.example.springbootdemo.serviceimpl.hui;

import com.example.springbootdemo.mapper.*;
import com.example.springbootdemo.mapper.hui.*;
import com.example.springbootdemo.model.*;
import com.example.springbootdemo.model.hui.*;
import com.example.springbootdemo.response.ResponseBo;
import com.example.springbootdemo.service.hui.TestService;
import com.example.springbootdemo.utils.MD5Util;
import com.example.springbootdemo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Resource
    USERSMapper usersMapper;
    @Resource
    IMAGESMapper imagesMapper;
    @Resource
    THUMBMapper thumbMapper;
    @Resource
    COLLECTSMapper collectsMapper;
    @Resource
    AUDITIMAGESMapper auditimagesMapper;
    @Resource
    ATTENTIONSMapper attentionsMapper;
    @Resource
    POINTSMapper pointsMapper;
    @Resource
    PURCHASEMapper purchaseMapper;
    @Resource
    DEPOSITSMapper depositsMapper;
    @Resource
    TAGSMapper tagsMapper;
    @Resource
    AUDITTAGSMapper audittagsMapper;
    @Resource
    COMMENTSMapper commentsMapper;
    @Resource
    COMPLAINSMapper complainsMapper;
    /**
     * 功能描述:注册
     * 作者: wangzenghui
     * 创建时间：2019/7/18 9:16
     */

    @Override
    public ResponseBo reg(USERS users) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=0;
        USERS selUser;
        selUser=usersMapper.selectByName(users.getName());
        if (selUser!=null) {
            responseBo.setResMsg("用户名已被注册，请更换用户名");
            return responseBo;
        }
        String password=new MD5Util().EncoderByMd5(users.getPassword());
        users.setPassword(password);
        res=usersMapper.reg(users);
        selUser=usersMapper.selectByName(users.getName());
        POINTS points=new POINTS();
        points.setUid(selUser.getId());
        points.setPoint(10);
        pointsMapper.insert(points);
        if (res==0){
            responseBo.setResMsg("注册失败");
            return responseBo;
        }
        responseBo.setResMsg("注册成功");
        return responseBo;
    }

    /**
     * 功能描述:登录
     * 作者: wangzenghui
     * 创建时间：2019/7/18 10:10
     */

    @Override
    public ResponseBo login(HttpServletRequest request,USERS users) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        USERS selUsers=usersMapper.selectByName(users.getName());
        if (selUsers==null) {
            responseBo.setResMsg("该用户不存在");
            return responseBo;
        }
        String password=new MD5Util().EncoderByMd5(users.getPassword());
        if (selUsers.getPassword().equals(password)==false){
            responseBo.setResMsg("密码错误");
            return responseBo;
        }
        if (selUsers.getBan()==1){
            responseBo.setResMsg("封禁账号");
            return responseBo;
        }
        responseBo.setResMsg("登录成功");
        responseBo.setResult(selUsers);
        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("users", selUsers);
        return responseBo;
    }

    /**
     * 功能描述:上传图片
     * 作者: wangzenghui
     * 创建时间：2019/7/19 14:45
     */

    @Override
    public ResponseBo upload(MultipartFile file,String title,String intro,int typeid,int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        if(file==null){
            responseBo.setResMsg("请上传文件");
            return responseBo;
        }
        String fileNames = file.getOriginalFilename();//这个fileNames是上传的文件名（不是地址）
        int split = fileNames.lastIndexOf(".");
        String suffix = fileNames.substring(split+1,fileNames.length());//文件后缀，用于判断上传的文件是否是合法的
        String newName;
        //判断文件类型，因为我这边是图片，所以只设置三种合法格式
        if(suffix.equals("jpg") || suffix.equals("png") || suffix.equals("gif")) {
            //正确的类型，保存文件
            byte[] bytes = file.getBytes();
            newName = new Date().getTime() + "."+suffix;
//                File savedFile = new File(paths+"/src/main/resources/static/images/"+newName);
            File savedFile = new File("D:/dhcProject/springbootdemo/images/"+newName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
            stream.write(bytes);
            stream.close();
            System.out.println("图片上传完毕，存储地址为："+savedFile.getAbsolutePath());
        }else {
            responseBo.setResMsg("图片类型不支持");
            return responseBo;
        }
        AUDITIMAGES auditimages=new AUDITIMAGES();
        auditimages.setImgname(newName);
        auditimages.setTitle(title);
        auditimages.setIntro(intro);
        auditimages.setTypeid(typeid);
        auditimages.setUserid(userid);
        int res=auditimagesMapper.insert(auditimages);
        if (res==1)
            responseBo.setResMsg("上传成功");
        else
            responseBo.setResMsg("上传失败");
        AUDITIMAGES images=auditimagesMapper.selectByImgname(newName);
        int imgid=images.getId();
        responseBo.setResult(imgid);
        return responseBo;
    }

    @Override
    public ResponseBo getImgs() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=new ArrayList();
        result=imagesMapper.selectAll();
        if (result.size()==0) {
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo update(HttpServletRequest request,MultipartFile file,String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        USERS users= (USERS) request.getSession().getAttribute("users");

        String fileNames="";
        String newName="defaultPic.jpg";


//        log.info(paths+"/src/main/resources/static/images/"+users.getPic());
        if (!users.getPic().equals("defaultPic.jpg")){
//            File oriPic=new File(paths+"/src/main/resources/static/images/"+users.getPic());
            File oriPic=new File("D:/dhcProject/springbootdemo/images/"+users.getPic());
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
//        File savedFile = new File(paths+"/src/main/resources/static/images/"+newName);//这里的路径根据自己的要求进行设置，有关路径配置的问题参考文章下半内容
        File savedFile = new File("D:/dhcProject/springbootdemo/images/"+newName);//这里的路径根据自己的要求进行设置，有关路径配置的问题参考文章下半内容
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
        stream.write(bytes);
        stream.close();
        System.out.println("图片上传完毕，存储地址为："+savedFile.getAbsolutePath());
    }else {
        responseBo.setResMsg("图片类型不支持");
        return responseBo;
    }}

        log.info("名字:"+name);
        if (name.equals(""))
            name=users.getName();
        users.setName(name);
        users.setPic(newName);
    int res=usersMapper.updateByPrimaryKeySelective(users);
        responseBo.setResult(users);
//        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("users", users);
        return responseBo;
}

    @Override
    public ResponseBo updatePass(String pass, String newpass, int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=0;
        USERS users=usersMapper.selectByPrimaryKey(userid);

        MD5Util md5Util=new MD5Util();
        if (!md5Util.EncoderByMd5(pass).equals(users.getPassword())){
            responseBo.setResMsg("原密码错误");
            return responseBo;
        }

        users.setPassword(md5Util.EncoderByMd5(newpass));
        res=usersMapper.updateByPrimaryKey(users);

        if (res==0){
            responseBo.setResMsg("修改失败");
            return responseBo;
        }
        responseBo.setResMsg("修改成功");
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="thumbandcolcache",allEntries=true),@CacheEvict(value = "imgscache",allEntries = true)})
    @Override
    public ResponseBo thumb(int imgid,int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
//        THUMB表防止用户重复点赞
        THUMB Thumb=thumbMapper.selectByImgAndUser(imgid,userid);
        IMAGES images2=imagesMapper.selectByPrimaryKey(imgid);
        if (Thumb!=null){
            responseBo.setResMsg("已点赞过");
            images2.setThumb(images2.getThumb()-1);
            imagesMapper.updateByPrimaryKeySelective(images2);
            thumbMapper.deleteByImgAndUser(imgid,userid);
            return responseBo;
        }
//        插入THUMB表
        Thumb=new THUMB();
        Thumb.setImgid(imgid);
        Thumb.setUserid(userid);
        int res2=thumbMapper.insert(Thumb);

//        更新IMAGES表信息
        IMAGES images=imagesMapper.selectByPrimaryKey(imgid);
        int thumb=images.getThumb();
        thumb+=1;
        images.setThumb(thumb);
        int res=imagesMapper.updateByPrimaryKeySelective(images);


        if (res==1&&res2==1)
            responseBo.setResMsg("点赞成功");
        responseBo.setResult(images);
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="thumbandcolcache",allEntries=true),@CacheEvict(value = "imgscache",allEntries = true)})
    @Override
    public ResponseBo collect(int imgid, int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        COLLECTS collects=collectsMapper.selectByImgAndUser(imgid,userid);
        if (collects!=null){
            responseBo.setResMsg("已收藏过");
            collectsMapper.deleteByImgAndUser(imgid,userid);
            return responseBo;
        }
        collects=new COLLECTS();
        collects.setImgid(imgid);
        collects.setUserid(userid);
        int res=collectsMapper.insert(collects);
        if (res!=1) {
            responseBo.setResMsg("收藏失败");
            return responseBo;
        }
        responseBo.setResMsg("收藏成功");
        return responseBo;
    }

    @Override
    public ResponseBo getImgsOnCollect(int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=new ArrayList();
        result=imagesMapper.selectCollect(userid);
        if (result.size()==0) {
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Cacheable(value = "thumbandcolcache")
    @Override
    public ResponseBo checkThumbAndCollect(int imgid, int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        THUMB thumb=thumbMapper.selectByImgAndUser(imgid,userid);
        COLLECTS collects=collectsMapper.selectByImgAndUser(imgid,userid);
        if (thumb!=null&&collects!=null){
            responseBo.setResMsg("已点赞,已收藏");
            return responseBo;
        }
        if (thumb!=null&&collects==null){
            responseBo.setResMsg("已点赞,未收藏");
            return responseBo;
        }
        if (thumb==null&&collects!=null){
            responseBo.setResMsg("未点赞,已收藏");
            return responseBo;
        }
        responseBo.setResMsg("未点赞,未收藏");
        return responseBo;
    }

    @Override
    public ResponseBo getImgById(int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        IMAGES images=imagesMapper.selectByPrimaryKey(imgid);
        if (images==null){
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(images);
        return responseBo;
    }

    @Override
    public ResponseBo getUserById(int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        USERS users=usersMapper.selectByPrimaryKey(userid);
        if (users==null){
            responseBo.setResMsg("没有查询到用户");
            return responseBo;
        }
        responseBo.setResMsg("查询成功");
        responseBo.setResult(users);
        return responseBo;
    }


    @Override
    public ResponseBo selectImgLikeName(String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=imagesMapper.selectLikeNameOrIntro(name);
        Set set=new HashSet();
        for (int i=0;i<result.size();i++){
            IMAGES images= (IMAGES) result.get(i);
            set.add(images.getId());
        }
        List tags=tagsMapper.selectLikeName(name);
        List result2=new ArrayList();
        for (int i=0;i<tags.size();i++) {
            TAGS temp= (TAGS) tags.get(i);
            int imgid=temp.getImgid();
            if (!set.contains(imgid))
                result2.add(imagesMapper.selectByPrimaryKey(imgid));
        }
        if (result==null&&tags==null){
            log.info("search方法结果 无查询结果");
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        result.addAll(result2);
        log.info("search方法结果 出参 result:"+result);
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo selectAudiImgLikeName(String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=auditimagesMapper.selectLikeName(name);
        if (result==null){
            log.info("search方法结果 无查询结果");
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        log.info("search方法结果 出参 result:"+result);
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo selectUserLikeName(String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=usersMapper.selectLikeName(name);
        if (result==null){
            log.info("search方法结果 无查询结果");
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        log.info("search方法结果 出参 result:"+result);
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo selectBanUserLikeName(String name) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=usersMapper.selectBanLikeName(name);
        if (result==null){
            log.info("search方法结果 无查询结果");
            responseBo.setResMsg("查询失败");
            return responseBo;
        }
        log.info("search方法结果 出参 result:"+result);
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Cacheable(value = "imgscache")
    @Override
    public ResponseBo getImgsByType(int typeid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=imagesMapper.selectByType(typeid);
        if (result==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("getImgsByType方法结果 出参 result:"+result);
        responseBo.setResMsg("查询成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Cacheable(value = "imgscache")
    @Override
    public ResponseBo selectAllOrderByThumb() throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List result=imagesMapper.selectAllOrderByThumb();
        if (result==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("selectAllOrderByThumb 方法 出参 result:"+result);
        responseBo.setResMsg("获取成功");
        responseBo.setResult(result);
        return responseBo;
    }

    @Override
    public ResponseBo getImgByUser(int userid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List images=imagesMapper.selectByUser(userid);
        if (images==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("getImgByUser 方法 出参 result:"+images);
        responseBo.setResMsg("获取成功");
        responseBo.setResult(images);
        return responseBo;
    }

    @Override
    public ResponseBo getFans(int atid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List images=attentionsMapper.selectFans(atid);
        if (images==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("getFans 方法 出参 result:"+images);
        responseBo.setResMsg("获取成功");
        responseBo.setResult(images);
        return responseBo;
    }

    @Override
    public ResponseBo getAt(int fanid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List images=attentionsMapper.selectAt(fanid);
        if (images==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("getAt 方法 出参 result:"+images);
        responseBo.setResMsg("获取成功");
        responseBo.setResult(images);
        return responseBo;
    }

    @Override
    public ResponseBo attention(int atid, int fanid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        ATTENTIONS attentions=new ATTENTIONS();

        int res=0;

        if (atid==fanid){
            responseBo.setResMsg("不能关注自己");
            return responseBo;
        }


        ATTENTIONS attentions1=attentionsMapper.selectByAtAndFan(atid,fanid);
        if (attentions1!=null){
            log.info("已关注过");
            responseBo.setResMsg("已关注过");
            return responseBo;
        }
        attentions.setAtid(atid);
        attentions.setFanid(fanid);
        res=attentionsMapper.insert(attentions);
        if (res==0){
            log.info("关注失败");
            responseBo.setResMsg("关注失败");
            return responseBo;
        }
        log.info("attention 方法 出参 res:"+res);
        responseBo.setResMsg("关注成功");
        return responseBo;
    }

    @Override
    public ResponseBo unattention(int atid, int fanid) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        int res=0;
        res=attentionsMapper.deleteByAtAndUser(atid,fanid);
        if (res==0){
            log.info("取消关注失败");
            responseBo.setResMsg("取消关注失败");
            return responseBo;
        }
        log.info("unattention 方法 出参 res:"+res);
        responseBo.setResMsg("取消关注成功");
        return responseBo;
    }

    @Override
    public ResponseBo logout(HttpServletRequest request) throws Exception {
        ResponseBo responseBo=new ResponseBo();


        responseBo.setResMsg("注销成功");
        request.getSession().invalidate();
        return responseBo;
    }

    @Cacheable(value = "pointscache")
    @Override
    public ResponseBo getPoints(int uid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        POINTS points =pointsMapper.selectByUid(uid);
        if (points==null){
            log.info("获取失败");
            responseBo.setResMsg("获取失败");
            return responseBo;
        }
        log.info("getPoints 方法 出参 result:"+points);
        responseBo.setResMsg("获取成功");
        responseBo.setResult(points);
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="pointscache",allEntries=true)})
    @Override
    public ResponseBo purchaseImg(int uid, int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        PURCHASE purchase=new PURCHASE();
        purchase.setImgid(imgid);
        purchase.setUid(uid);

        PURCHASE purchase1=purchaseMapper.selectByUserAndImg(uid,imgid);
        if (purchase1!=null){
            responseBo.setResMsg("已购买，不用再次购买");
            return responseBo;
        }

        POINTS points=pointsMapper.selectByUid(uid);
        if (points.getPoint()<1){
            responseBo.setResMsg("积分不足");
            return responseBo;
        }
        //        给购买者减少积分
        points.setPoint(points.getPoint()-1);
        pointsMapper.updateByPrimaryKey(points);

//        给图片发布者增加积分
        IMAGES images=imagesMapper.selectByPrimaryKey(imgid);
        int userid=images.getUserid();
        POINTS points1=pointsMapper.selectByUid(userid);
        points1.setPoint(points1.getPoint()+1);
        pointsMapper.updateByPrimaryKey(points1);



//        插入购买记录表
        purchaseMapper.insert(purchase);
        responseBo.setResMsg("购买成功");
        return responseBo;
    }

    @Override
    public ResponseBo checkPurchase(int uid, int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        PURCHASE purchase=purchaseMapper.selectByUserAndImg(uid,imgid);
        if (purchase==null){
            responseBo.setResMsg("未购买");
            return responseBo;
        }
        responseBo.setResMsg("已购买");
        return responseBo;
    }

    @Override
    public ResponseBo getImgPurchase(int uid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        List list =imagesMapper.selectPurchase(uid);
        if (list==null){
            log.info("该账户没有购买过");
            responseBo.setResMsg("该账户没有购买过");
            return responseBo;
        }
        responseBo.setResMsg("获取成功");
        responseBo.setResult(list);
        return responseBo;
    }

    @Override
    public ResponseBo deposit(DEPOSITS deposits) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=depositsMapper.insert(deposits);
        if (res==0){
            responseBo.setResMsg("插入失败");
            return responseBo;
        }
        responseBo.setResMsg("插入成功");
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="tagscache",allEntries=true)})
    @Override
    public ResponseBo addTags(int imgid, String tags) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        ArrayList<String> list= StringUtil.split(tags);
        for (int i=0;i<list.size();i++) {
            String tag=list.get(i);
            int imgidNum=imgid;
            int res=-1;
            TAGS dataTags=tagsMapper.selectByNameAndImgid(tag,imgidNum);
            if (dataTags==null) {
                TAGS newTags = new TAGS();
                newTags.setName(tag);
                newTags.setImgid(imgidNum);
                res = tagsMapper.insert(newTags);
            }
            if (res == 0) {
                responseBo.setResMsg("插入失败");
                responseBo.setResult(i);
                return responseBo;
            }
            responseBo.setResMsg("插入成功");
        }
        return responseBo;
    }

    @Override
    public ResponseBo addAuditTags(int imgid, String tags) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        ArrayList<String> list= StringUtil.split(tags);
        for (int i=0;i<list.size();i++) {
            String tag=list.get(i);
            int imgidNum=imgid;
            int res=-1;
            AUDITTAGS newTags = new AUDITTAGS();
            newTags.setName(tag);
            newTags.setImgid(imgidNum);
            res = audittagsMapper.insert(newTags);
            if (res == 0) {
                responseBo.setResMsg("添加失败");
                responseBo.setResult(i);
                return responseBo;
            }
            responseBo.setResMsg("添加成功");
        }
        return responseBo;
    }

    @Cacheable(value = "tagscache")
    @Override
    public ResponseBo getTags(String imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int imgidNum=Integer.parseInt(imgid);
        List list=tagsMapper.selectByImgid(imgidNum);
        int count=0;
        for (int i=0;i<list.size();i++){
            TAGS tags= (TAGS) list.get(i);
            count=tagsMapper.selectCountByTag(tags.getName());
            tags.setCount(count);
        }
        responseBo.setResult(list);
        responseBo.setResMsg("获取成功");
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="tagscache",allEntries=true)})
    @Override
    public ResponseBo delTags(int tagid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res=tagsMapper.deleteByPrimaryKey(tagid);
        if (res==0){
            responseBo.setResMsg("删除失败");
            return responseBo;
        }
        responseBo.setResMsg("删除成功");
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="commentscache",allEntries=true)})
    @Override
    public ResponseBo addComments(COMMENTS comments,int imgid) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        int res=commentsMapper.insert(comments);
        if (res==0){
            responseBo.setResMsg("添加失败");
            return responseBo;
        }
        responseBo.setResMsg("添加成功");
        return responseBo;
    }

    @Cacheable(value = "commentscache")
    @Override
    public ResponseBo getComments(int imgid) throws Exception {
        log.info("没有走缓存");
        ResponseBo responseBo=new ResponseBo();
        List list=commentsMapper.selectByImgid(imgid);
        responseBo.setResult(list);
        responseBo.setResMsg("获取成功");
        return responseBo;
    }

    @Caching(evict={@CacheEvict(value="commentscache",allEntries=true)})
    @Override
    public ResponseBo delComments(int comid) throws Exception {
        ResponseBo responseBo=new ResponseBo();
        int res = commentsMapper.deleteByPrimaryKey(comid);
        if (res==0){
            responseBo.setResMsg("删除失败");
            return responseBo;
        }
        responseBo.setResMsg("删除成功");
        return responseBo;
    }

    @Override
    public ResponseBo addComplains(COMPLAINS complains) throws Exception {
        ResponseBo responseBo=new ResponseBo();

        int res=complainsMapper.insert(complains);
        if (res==0){
            responseBo.setResMsg("添加失败");
            return responseBo;
        }
        responseBo.setResMsg("添加成功");
        return responseBo;
    }


}
