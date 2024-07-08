package com.itheima.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface CategoryMapper {


    @Insert("insert into category (category_name,category_alias,create_time,update_time,create_user)" +
            " values (#{categoryName},#{categoryAlias},#{createTime},#{updateTime},#{id})")
    void insertByCategoryName(String categoryName, String categoryAlias, LocalDateTime createTime, LocalDateTime updateTime, Integer id);
}
