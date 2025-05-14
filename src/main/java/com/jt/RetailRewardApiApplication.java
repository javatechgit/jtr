package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

<<<<<<< HEAD

@SpringBootApplication
@ComponentScan(basePackages = "com.jt.controller, com.jt.service,com.jt.repository,com.jt.entity") 
public class RetailRewardApiApplication  {
	
	
=======
@SpringBootApplication
@ComponentScan(value = {"com.jt.controller","com.jt.service","com.jt.repository","com.jt.entity","com.jt.model","com.jt.errors"})
public class RetailRewardApiApplication {

>>>>>>> 4468398 (commit changes)
	public static void main(String[] args) {
		SpringApplication.run(RetailRewardApiApplication.class, args);
	}

<<<<<<< HEAD
	


=======
>>>>>>> 4468398 (commit changes)
}
