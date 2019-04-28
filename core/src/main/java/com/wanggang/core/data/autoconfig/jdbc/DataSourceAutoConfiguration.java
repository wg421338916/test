package com.wanggang.core.data.autoconfig.jdbc;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-28 12:53
 **/
@Configuration
@ConditionalOnClass({DataSource.class, EmbeddedDatabaseType.class})
@EnableApolloConfig({"common.jdbc"})
public class DataSourceAutoConfiguration {

    @Primary
    @Bean
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
}
