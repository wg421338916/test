package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-25 16:46
 **/
@ConfigurationProperties(prefix = "my")
public class MyProperties {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
