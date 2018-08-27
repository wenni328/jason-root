package com.jason.service.impl;

import com.jason.entity.User;
import com.jason.mapper.UserMapper;
import com.jason.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public Integer insert(User user) {
        // 用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        user.setPassword(new SimpleHash("MD5", user.getPassword(), salt, 1024).toHex());
        return userMapper.insert(user);
    }

}
