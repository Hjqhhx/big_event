package com.itheima.mapper;

import com.itheima.annocation.AutoFill;
import com.itheima.enumeration.OperationType;
import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增文章分类
     *
     * @param category
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("insert into category (category_name,category_alias,create_time,update_time,create_user)" +
            " values (#{categoryName},#{categoryAlias},#{createTime},#{updateTime},#{createUser})")
    void insertByCategoryName(Category category);

    /**
     * 根据用户id查询文章
     *
     * @param CreateUserId
     * @return
     */
    @Select("select * from category where create_user = #{CreateUserId}")
    List<Category> selectCategoryByUserId(Integer CreateUserId);

    /**
     * 根据文章id查询文章详情
     *
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category selectCategoryDetailById(Integer id);

    /**
     * 根据id修改文章分类
     *
     * @param category
     * @return
     */
    @AutoFill(value = OperationType.UPDATE)
    @Update("update category" +
            " set category_name = #{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime}" +
            " where id=#{id}")
    void updateCategoryById(Category category);
}
