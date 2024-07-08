package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.service.CategoryService;
import com.itheima.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据文章分类插入
     *
     * @param params
     */
    @Override
    public void insertByCategoryName(Map<String, String> params) {

        //获取创建人id
        Map<String, Object> clams = ThreadLocalUtil.get();
        Integer id = (Integer) clams.get("id");
        //获取要插入的数据
        String categoryName = params.get("categoryName");
        String categoryAlias = params.get("categoryAlias");

        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime updateTime = LocalDateTime.now();

        categoryMapper.insertByCategoryName(categoryName, categoryAlias, createTime, updateTime,id);


    }
}
