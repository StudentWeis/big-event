package com.why.bigevent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bigevent.mapper.CommentRepository;
import com.why.bigevent.pojo.Comment;
import com.why.bigevent.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void add(Comment comment) {
        commentRepository.save(comment);
    }
}
