package com.wanggang.springbootstudy.services.autoconfig;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-22 13:33
 **/

public class MyService {

    private final MyProperties myProperties;

    public MyService(MyProperties myProperties, IConnection connection) {
        this.myProperties = myProperties;
    }

    public String getUserById() {
        return "my user";
    }

    public String getName() {
        return myProperties.getName();
    }
}
