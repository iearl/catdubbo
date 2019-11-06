package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel) {
        if (userModel.getUsername().isEmpty() || userModel.getPassword().isEmpty()) {
            return ResponseVO.fail("用户名或密码错误");
        }
        if (userAPI.register(userModel)) {
            return ResponseVO.success("注册成功");
        } else {
            return ResponseVO.fail("注册失败");
        }
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public ResponseVO check(String username) {
        //判断用户名是否存在
        if(username!=null && username.trim().length()>0){
            if (userAPI.checkUsername(username)) {
                return ResponseVO.success("用户名已存在");
            } else {
                return ResponseVO.success("用户名不存在");
            }
        } else {
            return ResponseVO.fail("用户名不能为空");
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseVO logout(String userName) {
        //前端保存7天的JWT信息，JWT刷新
        //服务端保存最近30分钟活跃用户信息，使用JWT中userId为key，查找用户
        /*
            退出功能：
                1. 前端删除JWT
                2. 后端删除活跃用户数缓存
                    可以使用redis缓存
         */
        return ResponseVO.success("用户退出成功");
    }

    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public ResponseVO getUserInfo() {
        //获得当前登录用户
        String userId = CurrentUser.getUserId();
        if (!userId.isEmpty()) {
            //将用户id传入后端进行查询
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfoModel = userAPI.getUserInfo(uuid);
            if (userInfoModel != null) {
                return ResponseVO.success(userInfoModel);
            } else {
                return ResponseVO.appFail("用户信息查询失败");

            }
        } else {
            return ResponseVO.fail("用户未登录");
        }
    }

    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public ResponseVO UpdateUserInfo(UserInfoModel infoModel) {
        //获得当前登录用户
        String userId = CurrentUser.getUserId();
        if (!userId.isEmpty()) {
            //将用户id传入后端进行查询
            int uuid = Integer.parseInt(userId);
            //判断当前登录人员id和修改人员id是否一致
            if (uuid != infoModel.getUuid()) {
                return ResponseVO.fail("请求该个人信息");
            }
            UserInfoModel userInfoModel = userAPI.updateUserInfo(infoModel);
            if (userInfoModel != null) {
                return ResponseVO.success(userInfoModel);
            } else {
                return ResponseVO.appFail("用户信息查询失败");
            }
        } else {
            return ResponseVO.fail("用户未登录");
        }
    }

}
