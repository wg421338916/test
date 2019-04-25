package com.wanggang.springbootstudy.services;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-25 15:15
 **/
@Configuration
public class ConditionalStudy {
    @Bean
    @Conditional({ACondition.class})
    public CharClass CharClassA() {
        return new CharClass('A');
    }

    @Bean
    @Conditional({BCondition.class,ACondition.class})//&& 的关系
    public CharClass CharClassB() {
        return new CharClass('B');
    }


    public class CharClass {
        private char c;

        public CharClass(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalStudy.class);

        Map<String, CharClass> map = applicationContext.getBeansOfType(CharClass.class);
        System.out.println(map);

    }
}

class ACondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return true;
    }
}

class BCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //获取ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //获取bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();


        return false;
    }
}