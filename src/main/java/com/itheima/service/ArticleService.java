package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.PageQuery;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 条件分页查询
     * @return
     */
    PageBean<Article> listQuery(PageQuery pageQuery);
}
