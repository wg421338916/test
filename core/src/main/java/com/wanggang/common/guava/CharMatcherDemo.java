package com.wanggang.common.guava;

import com.google.common.base.CharMatcher;

import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:http://ifeve.com/google-guava-strings/
 * @author: Mr.WG
 * @create: 2019-05-05 16:16
 **/
public class CharMatcherDemo {
    public static void main(String[] args) {
        String openId = com.google.common.base.CharMatcher.anyOf(",").trimFrom(",m,s,g,");
        System.out.println(openId);
        openId = com.google.common.base.CharMatcher.digit().trimFrom(",m,s,g,11");
        System.out.println(openId);


        System.out.println(TimeUnit.DAYS.toMillis(1));

        System.out.println(System.currentTimeMillis());

        String openId1 = CharMatcher.anyOf(",").replaceFrom("a,b,c.d", "/");
        System.out.println(openId1);
    }
}
