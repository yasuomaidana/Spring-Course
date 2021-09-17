package org.SpringStudy;

import org.SpringStudy.vehicle.Bike;
import org.SpringStudy.vehicle.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Car car(){
        return new Car();
    }
    @Bean
    public Bike bike(){
        return new Bike();
    }
}
