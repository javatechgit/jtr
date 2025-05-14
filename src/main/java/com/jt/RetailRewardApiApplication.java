package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.jt.controller","com.jt.service","com.jt.repository","com.jt.entity","com.jt.model","com.jt.errors"})
public class RetailRewardApiApplication {
<<<<<<< HEAD

=======
>>>>>>> feature
	public static void main(String[] args) {
		SpringApplication.run(RetailRewardApiApplication.class, args);
	}

}
