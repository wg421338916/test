package com.wanggang.core.data.autoconfig.mybatis;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-28 12:00
 **/
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean({DataSource.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
@EnableApolloConfig("common.mybatis")
public class MybatisAutoConfiguration {

    @Primary
    @Bean
    public MybatisProperties mybatisProperties() {
        return new MybatisProperties();
    }
}
