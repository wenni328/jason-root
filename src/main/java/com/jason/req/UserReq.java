package com.jason.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * User 请求参数实体
 *
 * @author xy
 */
@Data
public class UserReq {

    @NotBlank(message = "登陆用户名不能为空")
    @Length(min = 2, max = 20, message = "用户名2-20个字符")
    private String username;

    @NotBlank(message = "登陆密码不能为空")
    //@Pattern(regexp="([A-F0-9]{2}:){5}[A-F0-9]{2}", message="MAC地址格式不正确")
    private String password;
}
