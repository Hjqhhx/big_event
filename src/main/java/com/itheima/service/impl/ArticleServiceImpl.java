package com.itheima.service.impl;

import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 新增文章
     *
     * @param article
     */
    @Override
    public void insertArticle(Article article) {

        Map<String, Object> clams = ThreadLocalUtil.get();
        Integer id = (Integer) clams.get("id");
        article.setCreateUser(id);
        articleMapper.insertArticle(article);
    }
}
