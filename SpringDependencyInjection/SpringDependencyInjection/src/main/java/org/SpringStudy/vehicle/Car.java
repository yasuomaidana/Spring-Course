package org.SpringStudy.vehicle;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle{
    @Override
    public void drive(){
        System.out.println("Driving a car");
    }
}
