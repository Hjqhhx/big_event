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

    @GetMapping("/detail")
    @Operation(summary = "根据ID查询文章详情")
    public Result<Article> findArticleDetailById(Integer id) {

        Article article = articleService.findArticleDetailById(id);

        return Result.success(article);
    }

    /**
     * 根据ID修改文章信息
     *
     * @param article
     * @return
     */
    @PutMapping
    @Operation(summary = "根据ID修改文章")
    public Result updateArticleInfoById(@RequestBody @Validated Article article) {

        articleService.updateArticleInfoById(article);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "根据ID删除文章")
    public Result deleteArticleById(Integer id) {

        articleService.deleteArticleById(id);
        return Result.success();
    }
}
