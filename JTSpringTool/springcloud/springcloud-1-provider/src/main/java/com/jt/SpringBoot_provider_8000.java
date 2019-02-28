package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@MapperScan("com.jt.springcloud.mapper")
@EnableEurekaClient //启动服务客户端
@EnableHystrix  //开启断路器机制
public class SpringBoot_provider_8000 {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBoot_provider_8000.class, args);
	}
}
