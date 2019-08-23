package com.example.springbootdemo.controller.hui;

import com.example.springbootdemo.model.hui.PERSON;
import com.example.springbootdemo.model.hui.USERS;
import com.example.springbootdemo.response.ResponseBo;
import com.example.springbootdemo.service.hui.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Copyright (C) 2014-2016 
 * 功能描述: 本地PERSON表的增删改查
 * 作    者: wangzenghui
 * 创建时间: 2019/7/17 14:17
 * 修改记录:
 *      <时间>            <作者>        <版本>        <描述>
 *      2019/7/17 14:17  hui         1.0           初始创建
 *
 */
@RestController
@RequestMapping("/hui/TestController")
@Slf4j
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "/hello.do",method = RequestMethod.GET)
    public String hello(){
        return "Hello World";
    }

    /**
     * 功能描述:用户注册
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:22
     */
     
    @RequestMapping(value = "/reg.do",method = RequestMethod.GET)
    public ResponseBo reg(HttpServletRequest request){
        log.info("hui/TestController======>reg 进入方法");
        ResponseBo responseBo=new ResponseBo();
        USERS users=new USERS();
        try {
            String name=request.getParameter("name");
            String  password=request.getParameter("password");
            users.setName(name);
            users.setPassword(password);
            log.info("hui/TestController======>reg 进入接口,入参:person=",users);
            responseBo=testService.reg(users);
        } catch (Exception e) {
            log.error("hui/TestController======>reg 方法出错:{}",e);
            responseBo.setResMsg("注册失败");
            responseBo.setResult(e);
        }
        return responseBo;
    }

    /**
     * 功能描述:用户登录
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:24
     */

    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public ResponseBo  login(HttpServletRequest request){
        log.info("hui/TestController======>login 进入方法");
        ResponseBo responseBo=new ResponseBo();
        USERS users=new USERS();
        try {
            String name=request.getParameter("name");
            String  password=request.getParameter("password");
            users.setName(name);
            users.setPassword(password);
            log.info("hui/TestController======>login 进入接口,入参:users=",users);
            responseBo=testService.login(request,users);
        } catch (Exception e) {
            log.error("hui/TestController======>login 方法出错:{}",e);
            responseBo.setResMsg("登录失败");
            responseBo.setResult(e);
        }

        return responseBo;
    }

    /**
     * 功能描述:图片上传
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:24
     */

    @RequestMapping(value = "/upload.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  upload(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
        String title=request.getParameter("title");
        String intro=request.getParameter("intro");
        String type=request.getParameter("type");
        int typeid=Integer.parseInt(type);
//        log.info("type:"+typeid);
        USERS users=(USERS) request.getSession().getAttribute("users");
        int userid=users.getId();
        StandardMultipartHttpServletRequest  thisRequest = (StandardMultipartHttpServletRequest )request;
        MultipartFile file = thisRequest.getFile("pic");

            responseBo=testService.upload(file,title,intro,typeid,userid);
        } catch (Exception e) {
            log.error("上传出错",e);
            responseBo.setResMsg("上传失败");
            return responseBo;
        }
        return responseBo;
    }

    /**
     * 功能描述:获取所有图片
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:24
     */

    @RequestMapping(value = "/getImgs.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImg(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=testService.getImgs();
        } catch (Exception e) {
            log.error("查询出错",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:修改用户信息
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:24
     */

    @RequestMapping(value = "/update.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  update(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        String name=request.getParameter("name");
        StandardMultipartHttpServletRequest  thisRequest = (StandardMultipartHttpServletRequest )request;
        MultipartFile file = thisRequest.getFile("pic");
        try {
            responseBo=testService.update(request,file,name);
        } catch (Exception e) {
            log.error("修改出错",e);
            responseBo.setResMsg("修改失败");
        }
        log.info("TestController===>update方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:修改密码
     * 作者: wangzenghui
     * 创建时间：2019/7/30 11:35
     */

    @RequestMapping(value = "/updatePass.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  updatePass(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        String pass=request.getParameter("pass");
        String newpass=request.getParameter("newpass");
        String user=request.getParameter("userid");
        int userid=Integer.parseInt(user);
        try {
            responseBo=testService.updatePass(pass,newpass,userid);
        } catch (Exception e) {
            log.error("修改出错",e);
            responseBo.setResMsg("修改失败");
        }
        log.info("TestController===>updatePass方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:点赞
     * 作者: wangzenghui
     * 创建时间：2019/7/22 15:24
     */

    @RequestMapping(value = "/thumb.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  thumb(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        String imgid=request.getParameter("imgid");
        String userid=request.getParameter("userid");
        int imgid2=Integer.parseInt(imgid);
        int userid2=Integer.parseInt(userid);
        try {
            responseBo=testService.thumb(imgid2,userid2);
        } catch (Exception e) {
            log.error("点赞失败",e);
            responseBo.setResMsg("点赞失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:收藏图片
     * 作者: wangzenghui
     * 创建时间：2019/7/23 9:12
     */

    @RequestMapping(value = "/collect.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  collect(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String imgid=request.getParameter("imgid");
            String userid=request.getParameter("userid");
            int imgid2=Integer.parseInt(imgid);
            int userid2=Integer.parseInt(userid);
            responseBo=testService.collect(imgid2,userid2);
        } catch (Exception e) {
            log.error("收藏失败",e);
            responseBo.setResMsg("收藏失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:获取用户收藏的图片
     * 作者: wangzenghui
     * 创建时间：2019/7/23 11:24
     */

    @RequestMapping(value = "/getImgsOnCollect.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImgsOnCollect(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            USERS users=(USERS)request.getSession().getAttribute("users");
            int userid2=users.getId();
            responseBo=testService.getImgsOnCollect(userid2);
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:检测图片是否被当前用户点赞或收藏过
     * 作者: wangzenghui
     * 创建时间：2019/7/24 8:55
     */

    @RequestMapping(value = "/checkThumbAndCollect.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  checkThumbAndCollect(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String imgid=request.getParameter("imgid");
            String userid=request.getParameter("userid");
            int imgid2=Integer.parseInt(imgid);
            int userid2=Integer.parseInt(userid);
            responseBo=testService.checkThumbAndCollect(imgid2,userid2);
        } catch (Exception e) {
            log.error("检查失败",e);
            responseBo.setResMsg("检查失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:通过id获取图片
     * 作者: wangzenghui
     * 创建时间：2019/7/24 10:08
     */

    @RequestMapping(value = "/getImgById.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImgById(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String imgid=request.getParameter("imgid");
            int imgid2=Integer.parseInt(imgid);
            responseBo=testService.getImgById(imgid2);
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:通过id获取用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 10:09
     */

    @RequestMapping(value = "/getUserById.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getUserById(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String userid=request.getParameter("userid");
            int userid2=Integer.parseInt(userid);
            responseBo=testService.getUserById(userid2);
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:搜索图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 14:12
     */

    @RequestMapping(value = "/search.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  search(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String name=request.getParameter("name");
            String type=request.getParameter("type");
            log.info("进入search方法,入参 name:"+name);
            if (type==null||type.equals("图片"))
                responseBo=testService.selectImgLikeName(name);
            if (type.equals("用户"))
                responseBo=testService.selectUserLikeName(name);
            if (type.equals("封禁用户"))
                responseBo=testService.selectBanUserLikeName(name);
            if (type.equals("待审核图片"))
                responseBo=testService.selectAudiImgLikeName(name);
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:根据类型获取图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 15:03
     */

    @RequestMapping(value = "/getImgsByType.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImgsByType(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String type=request.getParameter("typeid");
            int typeid=Integer.parseInt(type);
            responseBo=testService.getImgsByType(typeid);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:获取根据点赞量排序的图片
     * 作者: wangzenghui
     * 创建时间：2019/7/29 8:37
     */

    @RequestMapping(value = "/selectAllOrderByThumb.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  selectAllOrderByThumb(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=testService.selectAllOrderByThumb();
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:根据用户id获取图片
     * 作者: wangzenghui
     * 创建时间：2019/7/29 8:58
     */

    @RequestMapping(value = "/getImgByUser.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImgByUser(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String user=request.getParameter("userid");
            int userid=Integer.parseInt(user);
            responseBo=testService.getImgByUser(userid);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:根据用户id获得粉丝信息
     * 作者: wangzenghui
     * 创建时间：2019/7/29 15:01
     */

    @RequestMapping(value = "/getFans.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getFans(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String user=request.getParameter("userid");
            int userid=Integer.parseInt(user);
            responseBo=testService.getFans(userid);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:根据用户id获得关注用户的信息
     * 作者: wangzenghui
     * 创建时间：2019/7/29 16:16
     */

    @RequestMapping(value = "/getAt.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getAt(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String user=request.getParameter("userid");
            int userid=Integer.parseInt(user);
            responseBo=testService.getAt(userid);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:关注用户
     * 作者: wangzenghui
     * 创建时间：2019/7/30 8:51
     */

    @RequestMapping(value = "/attention.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  attention(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String at=request.getParameter("atid");
            String fan=request.getParameter("fanid");
            int atid=Integer.parseInt(at);
            int fanid=Integer.parseInt(fan);
            responseBo=testService.attention(atid,fanid);
        } catch (Exception e) {
            log.error("关注失败",e);
            responseBo.setResMsg("关注失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:取消关注
     * 作者: wangzenghui
     * 创建时间：2019/7/30 8:51
     */

    @RequestMapping(value = "/unattention.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  unattention(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String at=request.getParameter("atid");
            String fan=request.getParameter("fanid");
            int atid=Integer.parseInt(at);
            int fanid=Integer.parseInt(fan);
            responseBo=testService.unattention(atid,fanid);
        } catch (Exception e) {
            log.error("取消关注失败",e);
            responseBo.setResMsg("取消关注失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:注销
     * 作者: wangzenghui
     * 创建时间：2019/7/31 16:21
     */

    @RequestMapping(value = "/logout.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  logout(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=testService.logout(request);
        } catch (Exception e) {
            log.error("注销失败",e);
            responseBo.setResMsg("注销失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:获取用户积分
     * 作者: wangzenghui
     * 创建时间：2019/8/7 11:17
     */

    @RequestMapping(value = "/getPoints.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getPoints(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String uid=request.getParameter("userid");
            int uid1=Integer.parseInt(uid);
            responseBo=testService.getPoints(uid1);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:购买图片
     * 作者: wangzenghui
     * 创建时间：2019/8/7 11:41
     */

    @RequestMapping(value = "/purchaseImg.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  purchaseImg(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String uid=request.getParameter("userid");
            String imgid=request.getParameter("imgid");
            int uid1=Integer.parseInt(uid);
            int imgid1=Integer.parseInt(imgid);
            responseBo=testService.purchaseImg(uid1,imgid1);
        } catch (Exception e) {
            log.error("购买失败",e);
            responseBo.setResMsg("购买失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:检查是否购买过
     * 作者: wangzenghui
     * 创建时间：2019/8/7 13:52
     */

    @RequestMapping(value = "/checkPurchase.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  checkPurchase(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String uid=request.getParameter("userid");
            String imgid=request.getParameter("imgid");
            int uid1=Integer.parseInt(uid);
            int imgid1=Integer.parseInt(imgid);
            responseBo=testService.checkPurchase(uid1,imgid1);
        } catch (Exception e) {
            log.error("检查失败",e);
            responseBo.setResMsg("检查失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:获取已购买的图片
     * 作者: wangzenghui
     * 创建时间：2019/8/7 14:26
     */

    @RequestMapping(value = "/getImgPurchase.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getImgPurchase(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 getImgPurchase 方法");
        try {
            String uid=request.getParameter("userid");
            int uid1=Integer.parseInt(uid);
            responseBo=testService.getImgPurchase(uid1);
        } catch (Exception e) {
            log.error("获取失败",e);
            responseBo.setResMsg("获取失败");
        }
        return responseBo;
    }
}
