package com.why.bigevent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.why.bigevent.pojo.Category;
import com.why.bigevent.pojo.Result;
import com.why.bigevent.service.CategoryService;
import com.why.bigevent.utils.ThreadLocalUtil;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return Result.success(categoryService.list(id));
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        return Result.success(categoryService.findById(id));
    }

    @PutMapping
    public Result<String> update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}
