package com.wanggang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * https://blog.csdn.net/DPnice/article/details/84028233
 */
@MapperScan("com.**.mapper")
@SpringBootApplication
public class SpringbootmybatisstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootmybatisstudyApplication.class, args);
    }

}
