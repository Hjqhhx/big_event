package com.itheima.service;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.PageQuery;

public interface ArticleService {
    /**
     * 新增文章
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 条件分页查询
     *
     * @return
     */
    PageBean<Article> listQuery(PageQuery pageQuery);

    /**
     * 根据ID查询文章详情
     *
     * @param id
     * @return
     */
    Article findArticleDetailById(Integer id);

    /**
     * 根据Id修改文章信息
     *
     * @param article
     */
    void updateArticleInfoById(Article article);

    /**
     * 根据Id删除文章
     *
     * @param id
     */
    void deleteArticleById(Integer id);
}
