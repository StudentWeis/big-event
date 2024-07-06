package com.why.bigevent.pojo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {

    @NotNull
    private Integer id;
    private String username;

    @JsonIgnore
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
