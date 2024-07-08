package com.itheima.controller;

import com.itheima.pojo.Article;
import com.itheima.pojo.Result;
import com.itheima.service.ArticleService;
import com.itheima.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
@Slf4j
@Tag(name = "文章相关接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @Operation(summary = "新增文章")
    public Result insertArticle(@RequestBody @Validated Article article) {

        articleService.insertArticle(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response) {

        try {
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
            return Result.success("分页查询成功");

        } catch (Exception e) {
            response.setStatus(401);
            return Result.error("未登录");
        }


    }
}
