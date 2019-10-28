package com.stylefeng.guns.rest.common;

/**
 * 使用ThreadLocal保存用户id
 */
public class CurrentUser {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void saveUserId(String userID){
        threadLocal.set(userID);
    }
    public static String getUserId(){
        return threadLocal.get();
    }
    /*
    //ThreadLocal每一个线程都会创建，存储数据量应该小,不建议存储用户整个信息
    //线程绑定的存储空间
    private static final ThreadLocal<UserInfoModel> threadLocal = new ThreadLocal<>();

    //将UserInfoModel保存到ThreadLocal中
    public static void saveUserInfo(UserInfoModel userInfoModel){
        threadLocal.set(userInfoModel);
    }
    //将UserInfoModel取出
    public static UserInfoModel getUserInfo(){
        return threadLocal.get();
    }*/
}
