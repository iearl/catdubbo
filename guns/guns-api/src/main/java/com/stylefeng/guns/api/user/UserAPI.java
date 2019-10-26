package com.stylefeng.guns.api.user;


public interface UserAPI {
    //登录
    boolean login(String username, String password);
    //注册
    boolean register(UserModel userModel);
    //检查用户名是否存在
    boolean checkUsername(String username);
    //查询用户信息
    UserInfoModel getUserInfo(int uuid);
    //更新用户信息
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
