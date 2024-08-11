package com.why.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void add(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        article.setId(id);
        article.setState("草稿");
        articleMapper.add(article);
        System.out.println("DAO 完毕" + LocalDateTime.now());

        // 发送消息到队列进行文本审核
        String queueName = "hello.queue2";
        rabbitTemplate.convertAndSend(queueName, article.getContent());
        System.out.println("Service 完毕" + LocalDateTime.now());
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
