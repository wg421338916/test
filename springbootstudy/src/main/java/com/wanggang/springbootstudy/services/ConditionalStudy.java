package com.wanggang.springbootstudy.services;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.print.DocFlavor;
import java.util.Map;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-25 15:15
 **/
//@ConditionalOnClass({String.class})//某个class位于类路径上，才会实例化一个Bean
//@ConditionalOnMissingClass({String.class})//某个class不位于类路径上，才会实例化一个Bean
@Configuration
public class ConditionalStudy {
    @Bean
    @Conditional({ BCondition.class,ACondition.class})//&& 的关系
//    @ConditionalOnBean(name = "CharClassA")
    public CharClass CharClassB() {
        return new CharClass('B');
    }

    @Bean
    @Conditional({ACondition.class})
    @ConditionalOnMissingBean(name = "CharClassB")//仅仅在当前上下文中不存在某个对象时，才会实例化一个Bea
//    @ConditionalOnBean(name = "CharClassB")
    public CharClass CharClassA() {
        return new CharClass('A');
    }




    public class CharClass {
        private char c;

        public CharClass(char c) {
            System.out.println(c);
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