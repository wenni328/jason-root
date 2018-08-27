package com.jason.mapper;

import com.jason.entity.User;

public interface UserMapper {

    User getUserByUsername(String username);

    Integer insert(User user);
}
