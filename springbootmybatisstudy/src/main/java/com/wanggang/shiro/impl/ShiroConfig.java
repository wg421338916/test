//package com.wanggang.shiro.impl;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 权限配置加载
// *
// * @author ruoyi
// */
////@Configuration
//public class ShiroConfig
//{
//    /**
//     * 自定义Realm
//     */
//    @Bean
//    public MyUserRealm myUserRealm()
//    {
//
////        SecurityManager
//        MyUserRealm userRealm = new MyUserRealm();
////        userRealm.setCacheManager(cacheManager);
//        return userRealm;
//    }
//
//    /**
//     * 安全管理器
//     */
//    @Bean
//    public SecurityManager securityManager(MyUserRealm myUserRealm)
//    {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 设置realm.
//        securityManager.setRealm(myUserRealm);
//
//        SecurityUtils.setSecurityManager(securityManager);
//
//        // 记住我
//       // securityManager.setRememberMeManager(rememberMeManager());
//        // 注入缓存管理器;
//       // securityManager.setCacheManager(getEhCacheManager());
//        // session管理器
//       // securityManager.setSessionManager(sessionManager());
//        return securityManager;
//    }
//
//}
