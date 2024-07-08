package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    /**
     * 根据文章分类插入
     *
     * @param category
     */
    void insertByCategoryName(Category category);


    /**
     * 根据用户信息查询所属文章信息
     *
     * @return
     */
    List<Category> findCategoryByUserId();

    /**
     * 根据文章id获取文章详情
     * @param id
     * @return
     */
    Category findCategoryDetailById(Integer id);

    /**
     * 根据id修改文章分类
     *
     * @param category
     * @return
     */
    void updateCategoryById(Category category);
}
