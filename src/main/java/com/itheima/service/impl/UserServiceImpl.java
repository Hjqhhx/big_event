package com.itheima.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.digest.DigestUtil;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询数据库中是否有该用户
     *
     * @param username 用户名
     * @return 返回查找到的对象
     */
    @Override
    public User findByUserName(String username) {

        return userMapper.selectByUserName(username);
    }

    /**
     * 没有该用户名时进行注册
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public void register(String username, String password) {

        //密码先进行加密处理
        String md5Password = DigestUtil.md5Hex(password); //使用糊涂包中的方法进行md5加密，也可以使用自己导入的工具包
        log.info("md5Password {}", md5Password);

        DateTime createTime = DateTime.now(); //糊涂包获取当前时间
        DateTime updateTime = DateTime.now();

        userMapper.addNewUser(username, md5Password, createTime, updateTime);
    }

    /**
     * 跟新用户信息
     *
     * @param user
     */
    @Override
    public void updateUserInfo(User user) {

        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * 跟新用户头像
     *
     * @param avatarUrl
     * @return
     */
    @Override
    public void updateUserAvater(String avatarUrl) {

        Map<String, Object> clams = ThreadLocalUtil.get();
        Integer id = (Integer) clams.get("id");
        User user = new User();
        user.setId(id);
        user.setUserPic(avatarUrl);
        user.setUpdateTime(LocalDateTime.now());

        userMapper.updateById(user);
    }

    /**
     * 修改用户密码
     *
     * @param params
     */
    @Override
    public void updateUserPwd(Map<String, String> params, Integer id) {


        User user = new User();
        user.setId(id);
        user.setPassword(DigestUtil.md5Hex(params.get("new_pwd")));
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);


    }
}
