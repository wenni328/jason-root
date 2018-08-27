package com.jason.service;

import com.jason.entity.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> list();

    Blog getBlogById(Integer id);
}
