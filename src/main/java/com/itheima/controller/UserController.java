package com.itheima.controller;

import cn.hutool.core.util.ObjectUtil;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {

        //查询用户
        User user = userService.findByUserName(username);
        //判断要注册的对象是否已经存在
        if (ObjectUtil.isEmpty(user)) {
            //没有占用，进行注册操作
            userService.register(username, password);
            return Result.success();
        } else {
            //占用，返回提示信息
            return Result.error("该用户名已存在");
        }

    }
}
