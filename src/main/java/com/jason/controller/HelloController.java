package com.jason.controller;

import com.jason.common.JasonResult;
import com.jason.common.aop.LogApi;
import com.jason.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "API - BlogController", description = "测试")
public class HelloController {
    @Autowired
    private BlogService blogService;


    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "查询博客详细信息", notes = "根据id查询博客信息")
    @ApiImplicitParam(name = "id", value = "博客id", required = true, dataType = "Integer")
    @LogApi("查询博客详细信息")
    public JasonResult detail(Integer id) {
        return JasonResult.ok(blogService.getBlogById(id));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "查询博客详细信息", notes = "根据id查询博客信息")
    @LogApi(value = "查询博客详细信息")
    public JasonResult listBlog() {
        return JasonResult.ok(blogService.list());
    }


}
