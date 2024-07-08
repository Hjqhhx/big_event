package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.PageQuery;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /**
     * 条件分页查询(使用分页查询插件完成，PageHelper）
     *
     * @return
     */
    @Override
    public PageBean<Article> listQuery(PageQuery pageQuery) {

        //1.开启pagehelper的分页查询
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        Map<String, Object> clams = ThreadLocalUtil.get();
        Integer id = (Integer) clams.get("id");
        pageQuery.setCreateId(id);
        //3.调用mapper执行sql
        List<Article> list = articleMapper.listQuery(pageQuery);

        //将list对象强转为Page对象（PageHelper中带的）调用Page对象的方法获取结果集
        Page<Article> page = (Page<Article>) list;

        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());

        return pageBean;
    }
}
