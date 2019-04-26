package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-26 09:58
 **/
@Configuration
@ConditionalOnClass(MyFirstConnection.class)
public class MyFirstConnectionConfiguration {

//    @Primary
    @Bean
    @Qualifier("MyFirstConnection")
//    @ConditionalOnMissingBean(IConnection.class)
    public MyFirstConnection MyFirstConnection() {
        return new MyFirstConnection();
    }
}
