package com.why.bigevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.why.bigevent.pojo.Comment;
import com.why.bigevent.pojo.Result;
import com.why.bigevent.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comment")
@Tag(name = "评论管理", description = "评论管理相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Operation(summary = "添加评论", description = "添加评论接口")
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }
}
