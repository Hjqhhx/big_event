package com.itheima.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "用户API")
@Slf4j
@Validated //开启@pattern注解
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @Operation(summary = "用户注册接口")
    @PostMapping("/register")
    //@Pattern(regexp = "^$\\S{5,16}")这个是spring validation 提供的校验参数的，里面是正则表达式
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {

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

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Operation(summary = "用户登录接口")
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {

        User loginUser = userService.findByUserName(username);
        if (ObjectUtil.isEmpty(loginUser)) return Result.error("用户不存在，请注册");

        if (loginUser.getPassword().equals(DigestUtil.md5Hex(password))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误，请重新输入");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/userinfo")
    @Operation(summary = "获取用户信息接口")
    public Result<User> userInfo() {

        //从ThreadLocal中获取，在拦截器中存进去的数据
        Map<String, Object> clams = ThreadLocalUtil.get();
        String username = (String) clams.get("username");
        User user = userService.findByUserName(username);

        return Result.success(user);
    }


    /**
     * 跟新用户信息
     *
     * @param user 用实体类接收前端参数，因为前端为json格式
     * @return 返回信息
     */
    @PutMapping("/update")
    @Operation(summary = "跟新用户信息接口")
    public Result updateUserInfo(@RequestBody User user) {

        userService.updateUserInfo(user);
        return Result.success();
    }

    /**
     * 跟新用户头像
     *
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    @Operation(summary = "跟新用户头像接口")
    public Result updateUserAvatar(@RequestParam @URL String avatarUrl) {

        userService.updateUserAvater(avatarUrl);
        return Result.success();

    }

    /**
     * 修改用户密码接口
     *
     * @param params
     * @return
     */
    @PatchMapping("/updatePwd")
    @Operation(summary = "跟新用户密码接口")
    public Result updateUserPwd(@RequestBody Map<String, String> params) {

        //获取参数信息
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        //从线程中获取当前用户的信息
        Map<String, Object> clams = ThreadLocalUtil.get();
        String username = (String) clams.get("username");
        Integer id = (Integer) clams.get("id");

        //将原始密码与原来的密码进行对比
        User user = userService.findByUserName(username);
        String password = user.getPassword();

        //加个非空判断
        if (StrUtil.isBlankIfStr(oldPwd)) return Result.error("原密码不能为空");

        //密码校验
        if (!password.equals(DigestUtil.md5Hex(oldPwd))) {
            return Result.error("原密码不对");
        }

        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写密码不一致");
        }

        userService.updateUserPwd(params, id);

        return Result.success();
    }
}
