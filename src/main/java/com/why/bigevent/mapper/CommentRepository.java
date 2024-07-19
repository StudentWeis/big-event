package com.why.bigevent.mapper;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.why.bigevent.pojo.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}
