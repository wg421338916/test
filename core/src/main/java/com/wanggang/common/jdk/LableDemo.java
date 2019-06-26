package com.wanggang.common.jdk;

import com.sun.net.httpserver.Authenticator;
import com.sun.org.apache.bcel.internal.generic.GOTO;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-26 15:56
 **/
public class LableDemo {
    public static void main(String[] args) {
        int i = 0;
        Retry:

        while (true) {
            i++;
            if (i == 20)
                break Retry;
            if (i == 1)
                continue Retry;


        }

        System.out.println("over" + i);
    }
}
