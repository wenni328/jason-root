package com.jason.common.shiro;

import com.jason.entity.Permission;
import com.jason.entity.Role;
import com.jason.entity.User;
import com.jason.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm 包含
 * 登陆认证和授权
 *
 * @author xy
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    private SimpleAuthenticationInfo info = null;

    /**
     * 认证---触发权限认证时候作用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户名
        String name = (String) principals.getPrimaryPrincipal();
        //查询用户名称
        User user = userService.getUserByUsername(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 将token装换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 获取用户名即可
        String username = upToken.getUsername();
        // 查询数据库，是否查询到用户名和密码的用户
        User userInfo = userService.getUserByUsername(username);
        if (userInfo != null) {
            // 如果查询到了，封装查询结果，返回给我们的调用
            Object principal = userInfo.getName();
            //存库的时候使用  String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
            Object credentials = userInfo.getPassword();
            // 获取盐值，即用户名
            ByteSource salt = ByteSource.Util.bytes(username);
            String realmName = this.getName();
            // 将账户名，密码，盐值，realmName实例化到SimpleAuthenticationInfo中交给Shiro来管理
            info = new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
        } else {
            // 如果没有查询到，抛出一个异常
            throw new AuthenticationException();
        }
        return info;
    }

}
