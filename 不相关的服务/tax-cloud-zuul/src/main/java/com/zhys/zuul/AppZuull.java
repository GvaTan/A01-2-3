package com.zhys.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.zhys.zuul.filter.AuthFilter;
import com.zhys.zuul.filter.AuthHeaderFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class AppZuull {

	 public static void main(String[] args) {
		SpringApplication.run(AppZuull.class, args);
	}
	 
	@Bean  
    public AuthFilter accessUserNameFilter() {  
        return new AuthFilter();  
    }  
      
    //@Bean  
    public AuthHeaderFilter accessPasswordFilter(){  
        return new AuthHeaderFilter();  
    } 
	
}
