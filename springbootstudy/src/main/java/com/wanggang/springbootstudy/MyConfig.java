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
    private Integer test;

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }
}
