package com.wanggang.springbootstudy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-22 11:30
 **/
@Configuration
public class MyConfig {
    @Value("${test}")
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
