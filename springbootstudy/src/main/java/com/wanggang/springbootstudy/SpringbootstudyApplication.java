package com.wanggang.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootstudyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootstudyApplication.class, args);

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean.getTest());
    }

}
