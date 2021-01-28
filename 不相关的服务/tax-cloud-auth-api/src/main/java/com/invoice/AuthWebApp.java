package com.invoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
//@EnableCircuitBreaker
@EnableHystrix
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AuthWebApp {

	 public static void main(String[] args) {
		 SpringApplication.run(AuthWebApp.class, args);
	}
	
}
