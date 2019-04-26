package com.wanggang.springbootstudy;

import com.ctrip.framework.apollo.core.enums.Env;
import com.ctrip.framework.apollo.core.spi.MetaServerProvider;
import com.ctrip.framework.foundation.internals.ServiceBootstrap;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class SpringbootstudyApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SpringbootstudyApplication.class, args);

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean.getTest());


    }

}
