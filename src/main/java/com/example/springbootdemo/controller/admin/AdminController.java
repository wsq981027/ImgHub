package com.example.springbootdemo.controller.admin;

import com.example.springbootdemo.model.COMPLAINS;
import com.example.springbootdemo.model.POINTS;
import com.example.springbootdemo.response.ResponseBo;
import com.example.springbootdemo.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2014-2016
 * 功能描述: 后台功能
 * 作    者: wangzenghui
 * 创建时间: 2019/9/6 14:30
 * 修改记录:
 *      <时间>            <作者>        <版本>        <描述>
 *      2019/9/6 14:30  hui         1.0           初始创建
 *
 */

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 功能描述:管理员登录
     * 作者: wangzenghui
     * 创建时间：2019/7/23 15:09
     */

    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public ResponseBo login(HttpServletRequest request){
        log.info("admin/AdminController======>login 进入方法");
        ResponseBo responseBo=new ResponseBo();
        String  name=request.getParameter("name");
        String  password=request.getParameter("password");
        try {
            log.info("admin/AdminController======>login 进入接口,入参:name=",name);
            responseBo=adminService.login(name,password);
        } catch (Exception e) {
            log.error("admin/AdminController======>login 登录方法出错:{}",e);
            responseBo.setResMsg("登录失败");
            responseBo.setResult(e);
        }
        return responseBo;
    }

    /**
     * 功能描述:获取所有用户信息
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:25
     */

    @RequestMapping(value = "/getUsers.do",method = RequestMethod.GET)
    public ResponseBo getUsers(HttpServletRequest request){
        log.info("admin/AdminController======>getUsers 进入方法");
        ResponseBo responseBo=new ResponseBo();
        try {
            log.info("admin/AdminController======>getUsers 进入接口,入参:无");
            responseBo=adminService.getUsers();
        } catch (Exception e) {
            log.error("admin/AdminController======>getUsers 方法出错:{}",e);
            responseBo.setResMsg("查询失败");
            responseBo.setResult(e);
        }
        return responseBo;
    }

    /**
     * 功能描述:通过id删除用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:25
     */

    @RequestMapping(value = "/deleteUserById.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  deleteUserById(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String userid=request.getParameter("userid");
            int userid2=Integer.parseInt(userid);
            responseBo=adminService.deleteUserById(userid2);
        } catch (Exception e) {
            log.error("删除失败",e);
            responseBo.setResMsg("删除失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:获取所有被封禁用户信息
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:26
     */

    @RequestMapping(value = "/getBanUsers.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getBanUsers(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=adminService.getBanUsers();
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:封禁用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:26
     */

    @RequestMapping(value = "/banUser.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  banUser(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String userid=request.getParameter("userid");
            int userid2=Integer.parseInt(userid);
            responseBo=adminService.banUser(userid2);
        } catch (Exception e) {
            log.error("封禁失败",e);
            responseBo.setResMsg("封禁失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:解封用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:27
     */

    @RequestMapping(value = "/unbanUser.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  unbanUser(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String userid=request.getParameter("userid");
            int userid2=Integer.parseInt(userid);
            responseBo=adminService.unbanUser(userid2);
        } catch (Exception e) {
            log.error("解封失败",e);
            responseBo.setResMsg("解封失败");
        }
        return responseBo;
    }

    /**
     * 功能描述:修改管理员信息
     * 作者: wangzenghui
     * 创建时间：2019/7/25 10:54
     */

    @RequestMapping(value = "/update.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  update(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String adminid=request.getParameter("adminid");
            String name=request.getParameter("name");
            int adminid2=Integer.parseInt(adminid);
            StandardMultipartHttpServletRequest thisRequest = (StandardMultipartHttpServletRequest )request;
            MultipartFile file = thisRequest.getFile("pic");
            responseBo=adminService.update(request,file,name,adminid2);
        } catch (Exception e) {
            log.error("更新失败",e);
            responseBo.setResMsg("更新失败");
        }
        log.info("AdminController===>update方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:根据id删除图片
     * 作者: wangzenghui
     * 创建时间：2019/7/25 10:56
     */

    @RequestMapping(value = "/deleteImgById.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  deleteImgById(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String imgid=request.getParameter("imgid");
            int imgid2=Integer.parseInt(imgid);
            responseBo=adminService.deleteImgById(imgid2);
        } catch (Exception e) {
            log.error("删除失败",e);
            responseBo.setResMsg("删除失败");
        }
        log.info("AdminController===>deleteImgById 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:添加新类型
     * 作者: wangzenghui
     * 创建时间：2019/7/25 16:22
     */

    @RequestMapping(value = "/addType.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  addType(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String type=request.getParameter("type");
            responseBo=adminService.addType(type);
        } catch (Exception e) {
            log.error("添加失败",e);
            responseBo.setResMsg("添加失败");
        }
        log.info("AdminController===>addType 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:获取所有类型
     * 作者: wangzenghui
     * 创建时间：2019/7/25 16:23
     */

    @RequestMapping(value = "/getTypes.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getTypes(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=adminService.getTypes();
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        log.info("AdminController===>getTypes 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:根据id获取类型
     * 作者: wangzenghui
     * 创建时间：2019/7/26 10:36
     */

    @RequestMapping(value = "/getTypeById.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getTypeById(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String type=request.getParameter("typeid");
            int typeid=Integer.parseInt(type);
            responseBo=adminService.getTypeByid(typeid);
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        log.info("AdminController===>getTypeById 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:修改类型
     * 作者: wangzenghui
     * 创建时间：2019/7/29 14:10
     */

    @RequestMapping(value = "/updateType.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  updateType(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String type=request.getParameter("typeid");
            String name=request.getParameter("name");
            int typeid=Integer.parseInt(type);
            responseBo=adminService.updateType(typeid,name);
        } catch (Exception e) {
            log.error("更新失败",e);
            responseBo.setResMsg("更新失败");
        }
        log.info("AdminController===>updateType 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:根据id删除类型
     * 作者: wangzenghui
     * 创建时间：2019/7/29 14:20
     */

    @RequestMapping(value = "/deleteType.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  deleteType(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String type=request.getParameter("typeid");
            int typeid=Integer.parseInt(type);
            responseBo=adminService.deleteType(typeid);
        } catch (Exception e) {
            log.error("删除失败",e);
            responseBo.setResMsg("删除成功");
        }
        log.info("AdminController===>deleteType 方法 出参:responseBo="+responseBo);
        return responseBo;
    }
    /**
     * 功能描述:获取待审核图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 11:28
     */

    @RequestMapping(value = "/getAuditImgs.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  getAuditImgs(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            responseBo=adminService.getAuditImgs();
        } catch (Exception e) {
            log.error("查询失败",e);
            responseBo.setResMsg("查询失败");
        }
        log.info("AdminController===>getAuditImgs 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:通过审核图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 11:29
     */

    @RequestMapping(value = "/auditImg.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  auditImg(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String img=request.getParameter("imgid");
            int imgid=Integer.parseInt(img);
            responseBo=adminService.auditImg(imgid);
        } catch (Exception e) {
            log.error("审核失败",e);
            responseBo.setResMsg("审核失败");
        }
        log.info("AdminController===>auditImg 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:不通过审核图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 11:30
     */

    @RequestMapping(value = "/unauditImg.do",method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBo  unauditImg(HttpServletRequest request){
        ResponseBo responseBo=new ResponseBo();
        try {
            String img=request.getParameter("imgid");
            int imgid=Integer.parseInt(img);
            responseBo=adminService.unauditImg(imgid);
        } catch (Exception e) {
            log.error("审核失败",e);
            responseBo.setResMsg("审核失败");
        }
        log.info("AdminController===>unauditImg 方法 出参:responseBo="+responseBo);
        return responseBo;
    }

    /**
     * 功能描述:获取充值表数据
     * 作者: wangzenghui
     * 创建时间：2019/9/10 10:40
     */

    @RequestMapping("/getDeposits.do")
    public ResponseBo getDeposits(){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 getDeposits 方法");
        try {
            responseBo=adminService.getDeposits();
        } catch (Exception e) {
            log.info("getDeposits 方法出现异常");
            e.printStackTrace();
        }
        return responseBo;
    }

    /**
     * 功能描述:给用户添加积分，删除充值表
     * 作者: wangzenghui
     * 创建时间：2019/9/10 10:51
     */

    @RequestMapping("/addPoint.do")
    public ResponseBo addPoint(POINTS points){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 addPoint 方法");
        try {
            responseBo=adminService.addPoint(points);
        } catch (Exception e) {
            log.info("addPoint 方法出现异常");
            e.printStackTrace();
        }
        return responseBo;
    }

    /**
     * 功能描述:获取评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 11:51
     */

    @RequestMapping("/getComments.do")
    public ResponseBo getComments(){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 getComments 方法");
        try {
            responseBo=adminService.getComments();
        } catch (Exception e) {
            log.info("getComments 方法出现异常");
            e.printStackTrace();
        }
        return responseBo;
    }

    /**
     * 功能描述:获取举报列表
     * 作者: wangzenghui
     * 创建时间：2019/9/12 13:20
     */

    @RequestMapping("/getComplains.do")
    public ResponseBo getComplains(){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 getComplains 方法");
        try {
            responseBo=adminService.getComplains();
        } catch (Exception e) {
            log.info("getComplains 方法出现异常");
            e.printStackTrace();
        }
        return responseBo;
    }

    /**
     * 功能描述:处理举报评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 14:13
     */

    @RequestMapping("/delComplains.do")
    public ResponseBo delComplains(int compid){
        ResponseBo responseBo=new ResponseBo();
        log.info("进入 delComplains 方法");
        try {
            responseBo=adminService.delComplains(compid);
        } catch (Exception e) {
            log.info("delComplains 方法出现异常");
            e.printStackTrace();
        }
        return responseBo;
    }
}
