package com.why.bigevent.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.why.bigevent.mapper.ArticleMapper;
import com.why.bigevent.pojo.Article;
import com.why.bigevent.pojo.PageBean;
import com.why.bigevent.service.ArticleService;
import com.why.bigevent.utils.ThreadLocalUtil;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        article.setId(id);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");

        List<Article> articles = articleMapper.list(userId, categoryId, state);
        Page<Article> page = (Page<Article>) articles;
        pb.setTotal(page.getTotal());
        pb.setItems(page);
        return pb;
    }

    @Override
    public Article detail(Integer id) {
        return articleMapper.detail(id);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }
}
