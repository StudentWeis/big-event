package com.why.bigevent.service;

import com.why.bigevent.pojo.User;

public interface UserService {

    User findUserByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl, Integer id);

    void updatePwd(String new_pwd, Integer id);

}
