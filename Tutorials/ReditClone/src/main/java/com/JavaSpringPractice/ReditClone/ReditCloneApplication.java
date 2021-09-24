package com.JavaSpringPractice.ReditClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class ReditCloneApplication {

	public static void main(String[] args) {
		//Setting application.properties
		// using https://www.logicbig.com/tutorials/spring-framework/spring-boot/different-ways-to-pass-application-properties.html
		ReditCloneApplication.setEnvironment();
		/* You can see your environmental variables using the following lines
		System.out.println(System.getProperty("spring.datasource.url"));
		System.getenv().forEach((key,val)-> System.out.println(key+val));
		*/
		SpringApplication.run(ReditCloneApplication.class, args);
	}
	private static void setEnvironment(){
		File envFile = new File("src/main/resources/.env");
		try {
			Scanner envReader = new Scanner(envFile);
			System.out.println("Variables loaded from .env");
			while(envReader.hasNext()){
				String[] variable = envReader.nextLine().split("=");
				System.setProperty(variable[0],variable.length==1? "":variable[1]);
				System.out.println(variable[0]+"="+System.getProperty(variable[0]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(".env file was not founded. " +
					"\nThe variables must be defined from: " +
					"\napplication.properties" +
					"\n\tenvironmental variables");
		}
	}
}
