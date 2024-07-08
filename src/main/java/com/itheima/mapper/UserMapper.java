package com.itheima.mapper;

import cn.hutool.core.date.DateTime;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 返回值
     */

    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);

    /**
     * 用户注册，向数据库表中添加新用户
     * @param username
     * @param md5Password
     * @param createTime
     * @param updateTime
     */
    @Insert("insert into user(username, password, create_time, update_time)" +
            " values (#{username},#{md5Password},#{createTime},#{updateTime})")
    void addNewUser(String username, String md5Password, DateTime createTime, DateTime updateTime);

    /**
     * 跟新用户信息
     * @param user
     */
    @Update("update user set nickname = #{nickname},email=#{email},update_time=#{updateTime} where id = #{id}")
    void updateById(User user);
}
