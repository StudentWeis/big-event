package com.why.bigevent.service;

import com.why.bigevent.pojo.Article;
import com.why.bigevent.pojo.PageBean;

public interface ArticleService {

    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article detail(Integer id);

    void update(Article article);

    void delete(Integer id);

}
