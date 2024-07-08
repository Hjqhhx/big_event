package com.itheima.service;

import com.itheima.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {
    /**
     * 根据用户名查询数据库中是否有该用户
     *
     * @param username 用户名
     * @return 返回查找到的对象
     */
    User findByUserName(String username);

    /**
     * 没有该用户名时进行注册
     *
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 跟新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 跟新用户头像
     * @param avatarUrl
     * @return
     */
    void updateUserAvater(String avatarUrl);

    /**
     * 修改用户密码
     * @param params
     */
    void updateUserPwd(Map<String, String> params,Integer id);
}
