package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.PageQuery;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Slf4j
@Tag(name = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 新增文章
     *
     * @param article
     * @return
     */
    @PostMapping
    @Operation(summary = "新增文章")
    public Result insertArticle(@RequestBody @Validated Article article) {

        articleService.insertArticle(article);
        return Result.success();
    }

    /**
     * 条件分页查询文章
     *
     * @param pageQuery
     * @return
     */
    @GetMapping
    @Operation(summary = "条件分页查询")
    public Result<PageBean<Article>> list(PageQuery pageQuery) {

        log.info("PageQuery:{}", pageQuery);
        PageBean<Article> pageBean = articleService.listQuery(pageQuery);

        return Result.success(pageBean);
    }
}
