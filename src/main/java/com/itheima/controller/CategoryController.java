package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@Tag(name = "文章分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增文章分类
     *
     * @param category
     * @return
     */
    @PostMapping
    @Operation(summary = "新增文章分类")
    public Result insertByCategoryName(@RequestBody @Validated(Category.Add.class) Category category) {

        categoryService.insertByCategoryName(category);
        return Result.success();
    }

    /**
     * 根据用户id查询文章分类
     *
     * @return
     */
    @GetMapping
    @Operation(summary = "查询文章分类")
    public Result<List<Category>> findCategoryByUserId() {

        List<Category> categoryList = categoryService.findCategoryByUserId();

        return Result.success(categoryList);
    }

    /**
     * 根据文章ID获取文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    @Operation(summary = "获取文章详情")
    public Result<Category> findCategoryDetailByID(Integer id) {

        Category category = categoryService.findCategoryDetailById(id);

        return Result.success(category);
    }

    /**
     * 根据id修改文章分类
     *
     * @param category
     * @return
     */
    @PutMapping
    @Operation(summary = "修改文章分类")
    public Result updateCategoryById(@RequestBody @Validated(Category.Update.class) Category category) {

        categoryService.updateCategoryById(category);
        return Result.success();
    }

    /**
     * 根据文章id删除文章分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @Operation(summary = "删除文章分类")
    public Result deleteCategoryById(Integer id) {

        categoryService.deleteCategoryById(id);
        return Result.success();
    }

}
