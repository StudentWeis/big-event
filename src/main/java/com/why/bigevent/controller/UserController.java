package com.why.bigevent.controller;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.why.bigevent.pojo.Result;
import com.why.bigevent.pojo.User;
import com.why.bigevent.service.UserService;
import com.why.bigevent.utils.JwtUtil;
import com.why.bigevent.utils.Md5Util;
import com.why.bigevent.utils.ThreadLocalUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Tag(name = "用户相关接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}", message = "用户名长度必须在5-16之间，且不能包含空格") String username,
            @Pattern(regexp = "^\\S{5,16}", message = "密码长度必须在5-16之间，且不能包含空格") String password) {
        // 查询数据库，判断用户名是否存在
        User user = userService.findUserByUsername(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户已存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}") String username,
            @Pattern(regexp = "^\\S{5,16}") String password) {
        // 查询数据库，判断用户名是否存在
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result.error("用户不存在");
        }

        // 判断密码 Md5 加密后是否正确
        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("userInfo")
    public Result<User> userinfo() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User u = userService.findUserByUsername(username);
        return Result.success(u);
    }

    @PutMapping("update")
    public Result<String> update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userService.updateAvatar(avatarUrl, id);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> params) {
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)) {
            return Result.error("缺少必要的信息");
        }

        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findUserByUsername(username);

        if (!Md5Util.getMD5String(old_pwd).equals(user.getPassword())) {
            return Result.error("旧密码错误");
        }

        if (!new_pwd.equals(re_pwd)) {
            return Result.error("两次输入的密码不一致");
        }

        Integer id = (Integer) claims.get("id");
        userService.updatePwd(Md5Util.getMD5String(new_pwd), id);

        return Result.success();
    }
}
