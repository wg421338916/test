//package com.wanggang.shiro.controllers;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.apache.shiro.subject.Subject;
//import org.springframework.web.bind.annotation.*;
//
//
///**
// * @program: test
// * @description:
// * @author: Mr.WG
// * @create: 2019-05-13 14:10
// **/
//@RestController
//public class LoginController {
//
//    @GetMapping("/login")
//    public String ajaxLogin(@RequestParam String username, @RequestParam String password, @RequestParam Boolean rememberMe) {
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
//        Subject user = SecurityUtils.getSubject();
//
//        try {
//            //4、登录，即身份验证
//            user.login(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//            return "error";
//        }
//
//        // AuthenticationException 或 其 子 类 ， 常 见 的 如 ：
//        // DisabledAccountException（禁用的帐号）、
//        // LockedAccountException（锁定的帐号）、
//        // LockedAccountException（锁定的帐号）、
//        // UnknownAccountException（错误的帐号）、
//        // ExcessiveAttemptsException（登录失败次数过多）、
//        // IncorrectCredentialsException （错误的凭证）、
//        // ExpiredCredentialsException（过期的凭证）等
//
//        return "ok";
//    }
//
//
//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/logout")
//    public void logout() {
//        Subject user = SecurityUtils.getSubject();
//
//        user.logout();
//    }
//
//    //注解的使用
//    @RequiresRoles("admin")
//    @RequiresPermissions("*:*:*")
//    @RequestMapping(value = "/create")
//    public String create() {
//        return "Create success!";
//    }
//
//    //    @RequiresRoles("guest")
//    @RequiresPermissions("test:test:test")
//    @RequestMapping(value = "/find")
//    public String find() {
//        return "find success!";
//    }
//}
