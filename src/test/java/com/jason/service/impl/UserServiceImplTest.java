package com.jason.service.impl;

import com.jason.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: xieyong
 * @date: 2018/8/23 16:45
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
@Autowired
    UserService userService;
    @Test
    public void getUserByUsername() {
        userService.getUserByUsername(null);
    }
}