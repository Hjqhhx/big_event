package com.itheima.controller;

import cn.hutool.core.util.ObjectUtil;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "用户API")
@Slf4j
@Validated //开启@pattern注解
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "注册请求")
    @PostMapping("/register")
    //@Pattern(regexp = "^$\\S{5,16}")这个是spring validation 提供的校验参数的，里面是正则表达式
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

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
