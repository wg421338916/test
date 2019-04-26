package com.wanggang.springbootstudy.services.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-25 16:52
 **/
@Configuration
@EnableConfigurationProperties(MyProperties.class)
@ConditionalOnClass(MyService.class)
@Import({MyFirstConnection.class, MySecondConnection.class})
public class MyServiceAutoConfiguration {
    @Bean
    @ConditionalOnBean(IConnection.class)
    public MyService myService(MyProperties myProperties, IConnection connection) {
        return new MyService(myProperties, connection);
    }
}
