package com.jason.service;

import com.jason.entity.User;

public interface UserService {

    User getUserByUsername(String username);

    Integer insert(User user);
}
