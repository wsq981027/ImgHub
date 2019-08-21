package com.example.springbootdemo.service.admin;

import com.example.springbootdemo.model.admin.hui.ADMINS;
import com.example.springbootdemo.model.hui.TYPES;
import com.example.springbootdemo.response.ResponseBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    /**
     * 功能描述:管理员登录
     * 作者: wangzenghui
     * 创建时间：2019/7/23 15:54
     */

    ResponseBo login(String name,String password) throws Exception;

    /**
     * 功能描述:获得所有用户信息
     * 作者: wangzenghui
     * 创建时间：2019/7/23 15:55
     */

    ResponseBo getUsers() throws Exception;

    /**
     * 功能描述:通过id删除用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 10:50
     */

    ResponseBo deleteUserById(int userid) throws Exception;

    /**
     * 功能描述:获取被封禁的用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:17
     */

    ResponseBo getBanUsers() throws Exception;

    /**
     * 功能描述:封禁用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:18
     */

    ResponseBo banUser(int userid) throws Exception;

    /**
     * 功能描述:解封用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 14:21
     */

    ResponseBo unbanUser(int userid) throws Exception;

    /**
     * 功能描述:更新管理员信息
     * 作者: wangzenghui
     * 创建时间：2019/7/25 8:50
     */

    ResponseBo update(HttpServletRequest request, MultipartFile file, String name,int adminid) throws Exception;

    /**
     * 功能描述:通过id删除图片
     * 作者: wangzenghui
     * 创建时间：2019/7/25 10:51
     */

    ResponseBo deleteImgById(int imgid) throws Exception;

    /**
     * 功能描述:添加新类型
     * 作者: wangzenghui
     * 创建时间：2019/7/25 15:02
     */

    ResponseBo addType(String name) throws Exception;

    /**
     * 功能描述:获得所有类型
     * 作者: wangzenghui
     * 创建时间：2019/7/25 16:19
     */

    ResponseBo getTypes() throws Exception;

    /**
     * 功能描述:根据id获取类型
     * 作者: wangzenghui
     * 创建时间：2019/7/26 10:26
     */

    ResponseBo getTypeByid(int typeid) throws Exception;

    /**
     * 功能描述:修改图片类型
     * 作者: wangzenghui
     * 创建时间：2019/7/29 14:07
     */

    ResponseBo updateType(int typeid,String typename) throws Exception;

    /**
     * 功能描述:根据id删除类型
     * 作者: wangzenghui
     * 创建时间：2019/7/29 14:19
     */

    ResponseBo deleteType(int typeid) throws Exception;

    /**
     * 功能描述:获取所有待审核的图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 10:34
     */

    ResponseBo getAuditImgs() throws Exception;

    /**
     * 功能描述:审核通过
     * 作者: wangzenghui
     * 创建时间：2019/7/26 11:19
     */

    ResponseBo auditImg(int imgid) throws Exception;

    /**
     * 功能描述:审核不通过
     * 作者: wangzenghui
     * 创建时间：2019/7/26 11:20
     */

    ResponseBo unauditImg(int imgid) throws Exception;
}
