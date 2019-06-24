package com.wanggang.common.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-06-21 11:04
 **/
public class StreamDemo {
    public static void main(String[] args) {
        List<String> demo =new ArrayList<>();
        long l = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());

        demo.add("2");
        demo.add("2");
        demo.add("2");
        demo.add("2");
        demo.add("2");
        demo.add("2");

        try {
            demo.parallelStream().forEach(t->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                throw new RuntimeException("");
            });
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        System.out.println(System.currentTimeMillis()-l);
    }
}
