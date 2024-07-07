package com.itheima.mapper;

import cn.hutool.core.date.DateTime;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 返回值
     */

    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);


    @Insert("insert into user(username, password, create_time, update_time)" +
            " values (#{username},#{md5Password},#{createTime},#{updateTime})")
    void addNewUser(String username, String md5Password, DateTime createTime, DateTime updateTime);
}
