package com.example.web.config;


import com.example.web.model.UserDomain;
import com.example.web.service.user.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("执行了授权");

        //授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //simpleAuthorizationInfo.addStringPermission("user:add");

        Subject subject = SecurityUtils.getSubject();
        UserDomain userDomain = (UserDomain)subject.getPrincipal();

        simpleAuthorizationInfo.addStringPermission(userDomain.getPerm());

        System.out.println("userDomain getPerm:"+userDomain.getPerm());

        return simpleAuthorizationInfo;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        String username = userToken.getUsername();
        if(username==null) return  null;
        String password = new String(userToken.getPassword());

        //连接数据库  根据名字查询用户
        UserDomain user = userService.getUserByName(username);
        if(user==null || !password.equals(user.getPwd())){
            return  null;
        }
        else
        {
            //构造安全数据并且返回
            /*
            ProfileResult result = null;
            Map map = new HashMap();
            List<Permission> permissionsByName = permissionService.findAll(map);
            result = new ProfileResult(user,permissionsByName);*/

            return new SimpleAuthenticationInfo(user,user.getPwd(), this.getName());//这里的东西将会被存储到SessionId里
        }
    }
}
