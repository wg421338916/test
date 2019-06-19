package com.wanggang.common.jdk;

import java.sql.Time;
import java.util.Date;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-17 09:46
 **/
public class DateTimeDemo {
    public static void main(String[] args) {
        System.out.println(new Date().getTime());

        System.out.println(new Date(new Date().getTime()));

        Time time = Time.valueOf("00:00:00");
        System.out.println(time.getTime());
        System.out.println(Time.valueOf("00:00:00"));

    }
}
