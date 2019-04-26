package com.wanggang.springbootstudy.services.autoconfig;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 09:52
 **/
public class MyFirstConnection implements IConnection {
    @Override
    public void test() {
        System.out.println("first");
    }
}
