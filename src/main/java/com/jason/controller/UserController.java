package com.jason.controller;

import com.jason.common.JasonResult;
import com.jason.common.aop.LogApi;
import com.jason.entity.User;
import com.jason.req.UserReq;
import com.jason.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JasonResult register(@RequestBody User user) {
        return JasonResult.ok(userService.insert(user));
    }

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    @LogApi(value = "登陆")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JasonResult login(@RequestBody @Valid UserReq user/*,BindingResult bindingResult*/) {
//		if(bindingResult.hasErrors()) {
//			return JasonResult.error();
//		}
        // 当前subject
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(true);
            subject.login(token);
        }
        return JasonResult.ok();
    }
}
