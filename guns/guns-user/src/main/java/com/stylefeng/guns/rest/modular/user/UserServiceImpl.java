package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 在微服务中，类名中带有service单词建议做为暴露服务的接口
 */
@Component
@Service(interfaceClass = UserAPI.class)
public class UserServiceImpl implements UserAPI{

    @Autowired
    private MoocUserTMapper moocUserTMapper;
    //注册
    @Override
    public boolean register(UserModel userModel) {
        //1. 获取注册信息，自动获得
        //2. 将注册信息转换为数据实体(和数据库字段一致)
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setUserPhone(userModel.getPhone());
        //创建时间和修改时间由数据库设置为当前时间
        //密码做加密算法，可参考密码+salt(shiro)方式
        moocUserT.setUserPhone(MD5Util.encrypt(userModel.getPassword()));
        //3. 将数据实体存入到数据库中,已经设置主键自增
        Integer insert = moocUserTMapper.insert(moocUserT);
        if(insert>0){
            return true;
        }else{
            return false;
        }
    }
    //登录
    @Override
    public int login(String username, String password) {
        //根据用户名查询
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);
        MoocUserT moocUserT1 = moocUserTMapper.selectOne(moocUserT);
        if(moocUserT1!=null && moocUserT.getUuid()>0){
            if(MD5Util.encrypt(password).equals(password)){
                return moocUserT.getUuid();
            }
        }
        return 0;
    }

    //检查用户名是否存在
    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<MoocUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name",username);
        Integer result = moocUserTMapper.selectCount(entityWrapper);
        if(result!=null &&result>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        return null;
    }
}
