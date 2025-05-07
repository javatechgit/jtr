package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.jt.controller, com.jt.service,com.jt.repository,com.jt.entity") 
public class RetailRewardApiApplication  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(RetailRewardApiApplication.class, args);
	}

	


}
