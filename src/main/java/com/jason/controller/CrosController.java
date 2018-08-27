package com.jason.controller;

import com.jason.common.JasonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 跨域测试
 *
 * @author xy
 */
@RestController
@RequestMapping(value = "/cros")
public class CrosController {

    @CrossOrigin("http://localhost:8080/v1/device/manager/queryDevice")
    @RequestMapping(value = "/cros")
    public JasonResult crosTest(Integer currentPage, Integer pageSize) {

        return JasonResult.ok("13");

    }
}
