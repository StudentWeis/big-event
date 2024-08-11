package com.why.bigevent.pojo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "comment")
@Data
public class Comment {
    @Id
    private String id;
    @Field("article_id")
    private Integer articleId;
    @Field("content")
    private String content;
    @Field("state")
    private String state;
    @Field("nickname")
    private String nickname;
    @Field("create_time")
    private LocalDateTime createTime;
    @Field("create_user")
    private Integer createUser;
}
