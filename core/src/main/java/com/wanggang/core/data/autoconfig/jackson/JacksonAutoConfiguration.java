package com.wanggang.core.data.autoconfig.jackson;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-28 12:57
 **/

@Configuration
@ConditionalOnClass({ObjectMapper.class, Jackson2ObjectMapperBuilder.class})
@EnableApolloConfig({"common.jackson"})
public class JacksonAutoConfiguration {
    @Primary
    @Bean
    public JacksonProperties jacksonProperties() {
        return new JacksonProperties();
    }
}
