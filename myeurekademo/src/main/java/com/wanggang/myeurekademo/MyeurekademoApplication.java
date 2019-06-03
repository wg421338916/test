package com.wanggang.myeurekademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyeurekademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyeurekademoApplication.class, args);
	}

}
