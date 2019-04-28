package com.wanggang.springbootstudy.controllers;

import com.wanggang.springbootstudy.domain.BlogModifiedEvent;
import com.wanggang.springbootstudy.domain.User;
import com.wanggang.springbootstudy.services.autoconfig.MyService;
import com.wanggang.springbootstudy.services.autoconfig.MyServiceV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-22 13:33
 **/

@RestController
public class MyController {
    private final MyService myService;

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);


    @Autowired
    private MyServiceV2 myServiceV2;

    @Autowired
    private ApplicationEventPublisher publisher;

    public MyController(final MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/testEvent")
    public String testEvent() {
        //do sth modiify blog
        publisher.publishEvent(new BlogModifiedEvent(true));

        publisher.publishEvent(new BlogModifiedEvent(false));

        return "ok";
    }

    @GetMapping("/test")
    public String test() {

        logger.debug("ok" + System.currentTimeMillis());

        myServiceV2.test1();
        myServiceV2.test2();
        return myService.getUserById() + myService.getName();
    }

    @PostMapping("/{appId:.+}")
    public String test2(@PathVariable String appId, @Valid @RequestBody User u) {
        return u.getName();
    }

    //127.0.0.1:8080/test3?list=1,2
    @PostMapping("/test3")
    public String test3(@RequestParam List<String> list) {
        return String.join(",", list);
    }

    //[
    //    "30101","30103","30104"
    //]
    //@RequestBody 需要
    //参考： https://blog.csdn.net/wd2014610/article/details/80708173
    @PostMapping("/test4")
    public String test4(@RequestBody List<String> list) {
        return String.join(",", list);
    }
}
