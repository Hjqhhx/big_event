package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/category")
@Tag(name = "文章分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Operation(summary = "新增文章分类")
    public Result insertByCategoryName(@RequestBody Map<String, String> params) {

        categoryService.insertByCategoryName(params);
        return Result.success();
    }
}
