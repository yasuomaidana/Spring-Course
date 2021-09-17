package org.SpringStudy;

import org.SpringStudy.vehicle.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigNamedBeans {
    @Bean("myNamedCar")
    public Car car(){return new Car();}
}
