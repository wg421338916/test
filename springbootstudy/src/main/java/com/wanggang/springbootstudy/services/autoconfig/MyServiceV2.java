package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 11:52
 **/
@Service
public class MyServiceV2 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("MyFirstConnection")
    IConnection connection;


    public void test1(){
        stringRedisTemplate.opsForGeo();
        connection.test();
    }

    public void test2(){
        connection.test();
    }
}
