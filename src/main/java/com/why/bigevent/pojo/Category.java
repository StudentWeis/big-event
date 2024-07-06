package com.why.bigevent.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class Category {
    
    public interface Update extends Default {
    }

    @NotNull(groups = Update.class)
    private Integer id; // 主键ID

    @NotEmpty
    private String categoryName; // 分类名称

    @NotEmpty
    private String categoryAlias; // 分类别名

    @JsonIgnore
    private Integer createUser; // 创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; // 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; // 更新时间
}
