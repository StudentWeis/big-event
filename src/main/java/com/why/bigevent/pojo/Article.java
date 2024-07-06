package com.why.bigevent.pojo;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class Article {

    public interface Update extends Default {
    }

    @NotNull(groups = Update.class)
    private Integer id; // 主键ID

    @NotEmpty
    @Pattern(regexp =  "\\S{1,10}", message = "标题长度必须在1-10之间")
    private String title; // 文章标题

    @NotEmpty
    private String content; // 文章内容

    @NotEmpty
    @URL
    private String coverImg; // 封面图像

    @NotEmpty
    @Pattern(regexp =  "已发布|草稿", message = "状态只能是已发布或草稿")
    private String state; // 发布状态 已发布|草稿

    @NotNull
    private Integer categoryId; // 文章分类id
    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
