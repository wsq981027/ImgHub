package com.example.springbootdemo.service.hui;

import com.example.springbootdemo.model.COMMENTS;
import com.example.springbootdemo.model.COMPLAINS;
import com.example.springbootdemo.model.DEPOSITS;
import com.example.springbootdemo.model.hui.USERS;
import com.example.springbootdemo.response.ResponseBo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface TestService {

    /**
     * 功能描述:注册
     * 作者: wangzenghui
     * 创建时间：2019/7/18 10:08
     */

    ResponseBo reg(USERS users) throws Exception;

    /**
     * 功能描述:登录
     * 作者: wangzenghui
     * 创建时间：2019/7/18 10:08
     */

    ResponseBo login(HttpServletRequest request, USERS users) throws Exception;

    /**
     * 功能描述:上传图片
     * 作者: wangzenghui
     * 创建时间：2019/7/19 14:45
     */

    ResponseBo upload(MultipartFile file, String title, String intro, int typeid, int userid) throws Exception;

    /**
     * 功能描述:获取所有图片
     * 作者: wangzenghui
     * 创建时间：2019/7/19 15:03
     */

    ResponseBo getImgs() throws Exception;

    /**
     * 功能描述:更新用户信息
     * 作者: wangzenghui
     * 创建时间：2019/7/22 10:44
     */

    ResponseBo update(HttpServletRequest request, MultipartFile file, String name) throws Exception;

    /**
     * 功能描述:修改密码
     * 作者: wangzenghui
     * 创建时间：2019/7/30 11:29
     */

    ResponseBo updatePass(String pass, String newpass, int userid) throws Exception;

    /**
     * 功能描述:点赞图片
     * 作者: wangzenghui
     * 创建时间：2019/7/23 9:05
     */

    ResponseBo thumb(int imgid, int userid) throws Exception;

    /**
     * 功能描述:收藏图片
     * 作者: wangzenghui
     * 创建时间：2019/7/23 9:05
     */

    ResponseBo collect(int imgid, int userid) throws Exception;

    /**
     * 功能描述:查询用户收藏的图片
     * 作者: wangzenghui
     * 创建时间：2019/7/23 9:18
     */

    ResponseBo getImgsOnCollect(int userid) throws Exception;

    /**
     * 功能描述:检查图片是否收藏过
     * 作者: wangzenghui
     * 创建时间：2019/7/23 11:22
     */

    ResponseBo checkThumbAndCollect(int imgid, int userid) throws Exception;

    /**
     * 功能描述:通过id获取图片
     * 作者: wangzenghui
     * 创建时间：2019/7/24 10:05
     */

    ResponseBo getImgById(int imgid) throws Exception;

    /**
     * 功能描述:通过id获取用户
     * 作者: wangzenghui
     * 创建时间：2019/7/24 10:06
     */

    ResponseBo getUserById(int userid) throws Exception;

    /**
     * 功能描述:通过名字模糊查询图片
     * 作者: wangzenghui
     * 创建时间：2019/7/24 16:14
     */

    ResponseBo selectImgLikeName(String name) throws Exception;

    /**
     * 功能描述:通过名字模糊查询待审核图片
     * 作者: wangzenghui
     * 创建时间：2019/7/31 10:40
     */

    ResponseBo selectAudiImgLikeName(String name) throws Exception;

    /**
     * 功能描述:通过名字模糊查询用户
     * 作者: wangzenghui
     * 创建时间：2019/7/30 17:06
     */

    ResponseBo selectUserLikeName(String name) throws Exception;

    /**
     * 功能描述:通过名字模糊查询封禁用户
     * 作者: wangzenghui
     * 创建时间：2019/7/31 10:36
     */

    ResponseBo selectBanUserLikeName(String name) throws Exception;

    /**
     * 功能描述:根据类型id获取图片
     * 作者: wangzenghui
     * 创建时间：2019/7/26 14:10
     */

    ResponseBo getImgsByType(int typeid) throws Exception;

    /**
     * 功能描述:获取根据点赞量排序的图片
     * 作者: wangzenghui
     * 创建时间：2019/7/29 8:36
     */

    ResponseBo selectAllOrderByThumb() throws Exception;

    /**
     * 功能描述:通过用户id查询图片
     * 作者: wangzenghui
     * 创建时间：2019/7/29 8:56
     */

    ResponseBo getImgByUser(int userid) throws Exception;

    /**
     * 功能描述:根据用户id获得粉丝信息
     * 作者: wangzenghui
     * 创建时间：2019/7/29 14:58
     */

    ResponseBo getFans(int atid) throws Exception;

    /**
     * 功能描述:根据用户id获得关注用户的信息
     * 作者: wangzenghui
     * 创建时间：2019/7/29 16:14
     */

    ResponseBo getAt(int fanid) throws Exception;

    /**
     * 功能描述:关注用户
     * 作者: wangzenghui
     * 创建时间：2019/7/29 15:16
     */

    ResponseBo attention(int atid, int fanid) throws Exception;

    /**
     * 功能描述:取消关注
     * 作者: wangzenghui
     * 创建时间：2019/7/30 8:44
     */

    ResponseBo unattention(int atid, int fanid) throws Exception;

    /**
     * 功能描述:注销
     * 作者: wangzenghui
     * 创建时间：2019/7/31 16:18
     */

    ResponseBo logout(HttpServletRequest request) throws Exception;

    /**
     * 功能描述:获取用户积分
     * 作者: wangzenghui
     * 创建时间：2019/8/7 11:13
     */

    ResponseBo getPoints(int uid) throws Exception;

    /**
     * 功能描述:购买图片
     * 作者: wangzenghui
     * 创建时间：2019/8/7 11:38
     */

    ResponseBo purchaseImg(int uid,int imgid) throws Exception;

    /**
     * 功能描述:检查是否购买过
     * 作者: wangzenghui
     * 创建时间：2019/8/7 11:57
     */

    ResponseBo checkPurchase(int uid,int imgid) throws Exception;

    /**
     * 功能描述:获取购买过的图片
     * 作者: wangzenghui
     * 创建时间：2019/8/7 14:27
     */

    ResponseBo getImgPurchase(int uid) throws Exception;

    /**
     * 功能描述:用户充值
     * 作者: wangzenghui
     * 创建时间：2019/9/10 9:45
     */

    ResponseBo deposit(DEPOSITS deposits) throws Exception;

    /**
     * 功能描述:添加标签
     * 作者: wangzenghui
     * 创建时间：2019/9/10 14:22
     */

    ResponseBo addTags(int imgid,String tags) throws Exception;

    /**
     * 功能描述:添加待审核标签
     * 作者: wangzenghui
     * 创建时间：2019/9/10 15:22
     */

    ResponseBo addAuditTags(int imgid,String tags) throws Exception;

    /**
     * 功能描述:获取标签
     * 作者: wangzenghui
     * 创建时间：2019/9/10 15:41
     */

    ResponseBo getTags(String imgid) throws Exception;

    /**
     * 功能描述:删除标签
     * 作者: wangzenghui
     * 创建时间：2019/9/10 15:41
     */

    ResponseBo delTags(int tagid) throws Exception;

    /**
     * 功能描述:添加评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 17:25
     */

    ResponseBo addComments(COMMENTS comments,int imgid) throws Exception;

    /**
     * 功能描述:获取评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 17:41
     */

    ResponseBo getComments(int imgid) throws Exception;

    /**
     * 功能描述:删除评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 18:47
     */

    ResponseBo delComments(int comid) throws Exception;

    /**
     * 功能描述:举报评论
     * 作者: wangzenghui
     * 创建时间：2019/9/12 13:33
     */

    ResponseBo addComplains(COMPLAINS complains) throws Exception;

}
