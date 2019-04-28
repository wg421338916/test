package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 11:52
 **/
@Service
public class MyServiceV2 {
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("MyFirstConnection")
    IConnection connection;

    @Autowired
    RedisProperties properties;

    public void test1() {
        properties.toString();


        connection.test();

//        stringRedisTemplate.opsForHash().put("test", "test", "test");
    }

    public void test2() {
        connection.test();
    }
}
