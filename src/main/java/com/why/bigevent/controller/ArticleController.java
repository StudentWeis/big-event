package com.why.bigevent.controller;

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

import com.why.bigevent.pojo.Article;
import com.why.bigevent.pojo.PageBean;
import com.why.bigevent.pojo.Result;
import com.why.bigevent.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result<String> add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state) {
        return Result.success(articleService.list(pageNum, pageSize, categoryId, state));
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam Integer id) {
        return Result.success(articleService.detail(id));
    }

    @PutMapping
    public Result<String> update(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}
