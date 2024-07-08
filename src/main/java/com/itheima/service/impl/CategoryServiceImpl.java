package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据文章分类插入
     *
     * @param category
     */
    @Override
    public void insertByCategoryName(Category category) {

        Map<String, Object> clams = getClams();
        Integer createUser = (Integer) clams.get("id");
        category.setCreateUser(createUser);

        categoryMapper.insertByCategoryName(category);


    }


    @Override
    public List<Category> findCategoryByUserId() {

        Map<String, Object> clams = getClams();
        Integer id = (Integer) clams.get("id");

        return categoryMapper.selectCategoryByUserId(id);
    }

    /**
     * 根据文章id查询文章详情
     *
     * @param id
     * @return
     */
    @Override
    public Category findCategoryDetailById(Integer id) {
        return categoryMapper.selectCategoryDetailById(id);
    }

    /**
     * 根据id修改文章分类
     *
     * @param category
     * @return
     */
    @Override
    public void updateCategoryById(Category category) {

        categoryMapper.updateCategoryById(category);
    }

    /**
     * 根据文章id删除文章分类
     *
     * @param id
     * @return
     */
    @Override
    public void deleteCategoryById(Integer id) {

        categoryMapper.deleteCategoryById(id);
    }


    //从当前线程中获取已存储的载荷
    private Map<String, Object> getClams() {
        return ThreadLocalUtil.get();
    }
}
