package com.itheima.service;

import java.util.Map;

public interface CategoryService {
    /**
     * 根据文章分类插入
     * @param params
     */
    void insertByCategoryName(Map<String, String> params);
}
