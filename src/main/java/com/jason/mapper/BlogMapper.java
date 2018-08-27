package com.jason.mapper;

import com.jason.entity.Blog;

import java.util.List;

public interface BlogMapper {

    List<Blog> list();

    Blog getBlogById(Integer id);
}
