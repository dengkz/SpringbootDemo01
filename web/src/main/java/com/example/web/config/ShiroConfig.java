package com.example.web.config;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 3
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //通用配置（跳转登录页面，未授权跳转的页面）
        bean.setLoginUrl("/tologin");
        //未授权的url
        bean.setUnauthorizedUrl("/unauth");//NoPermissionException解决不跳转
        bean.setSuccessUrl("/index");


        /**
         * anon 无需认证就可访问
         * authc 必须认证才能访问
         * user
         * perms 拥有某个资源权限才能访问
         * role拥有某个角色权限才能访问
         * 只有perms，roles，ssl，rest，port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter，所以unauthorizedUrl设置后页面不跳转
         */

        // 拦截
        Map<String, String> shiroFilter = new LinkedHashMap<String, String>();
        //anon -- 匿名访问
        shiroFilter.put("/login","anon");
        shiroFilter.put("/tologin","anon");
        shiroFilter.put("/unauth","anon");
        shiroFilter.put("/index","anon");

        //注册 authc -- 认证之后访问（登录）
        //shiroFilter.put("/jdbc","perms[user:add]");
        shiroFilter.put("/*","authc");

        //perms -- 具有某中权限 (使用注解配置授权)
        //shiroFilter.put("/test1","perms");
        //shiroFilter.put("/test2","perms");


        bean.setFilterChainDefinitionMap(shiroFilter);


        return bean;
    }

    // 2
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") com.example.web.config.UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //关联userRealm
        defaultWebSecurityManager.setRealm(userRealm);

        return  defaultWebSecurityManager;
    }

    //1 创建Realm
    @Bean
    public com.example.web.config.UserRealm userRealm(){
        return  new com.example.web.config.UserRealm();
    }


}
