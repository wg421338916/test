package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 09:58
 **/
@Configuration
@ConditionalOnClass(MyFirstConnection.class)
public class MyFirstConnectionConfiguration {

    @Bean
    @ConditionalOnMissingBean(IConnection.class)
    public MyFirstConnection MyFirstConnection() {
        return new MyFirstConnection();
    }
}
