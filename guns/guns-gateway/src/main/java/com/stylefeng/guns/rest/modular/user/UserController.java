package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;

    @RequestMapping("register")
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
}
