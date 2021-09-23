package com.JavaSpringPractice.ReditClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReditCloneApplication {

	public static void main(String[] args) {
		//Setting application.properties
		// using https://www.logicbig.com/tutorials/spring-framework/spring-boot/different-ways-to-pass-application-properties.html
		System.setProperty("spring.datasource.url","jdbc:mysql://localhost:3306/reddit");
		SpringApplication.run(ReditCloneApplication.class, args);
	}

}
