package com.jason.service.impl;

import com.jason.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: xieyong
 * @date: 2018/8/23 16:40
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogServiceImplTest {
@Autowired
    BlogService blogService;
    @Test
    public void list() {
        blogService.list().forEach(o->{
            System.out.println(o.getAuthor());
        });
    }

    @Test
    public void getBlogById() {

    }
}