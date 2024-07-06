package com.why.bigevent.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.why.bigevent.mapper.UserMapper;
import com.why.bigevent.pojo.User;
import com.why.bigevent.service.UserService;
import com.why.bigevent.utils.Md5Util;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        // 密码加密
        String md5Password = Md5Util.getMD5String(password);
        
        // 保存到数据库
        userMapper.add(username, md5Password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl, Integer id) {
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String new_pwd, Integer id) {
        userMapper.updatePwd(new_pwd, id);
    }

}
