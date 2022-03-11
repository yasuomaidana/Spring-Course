package com.JavaSpringPractice.ReditClone;

import com.JavaSpringPractice.ReditClone.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
@EnableAsync //Enables async functionalities
@Import(SwaggerConfiguration.class)
public class ReditCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReditCloneApplication.class, args);
	}
}
