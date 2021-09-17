package org.SpringStudy.setters;

import org.SpringStudy.vehicle.Bike;
import org.SpringStudy.vehicle.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotUsingSetters {
    @Autowired
    Car car;
    @Autowired
    Bike bike;
    public void driveAll(){
        car.drive();
        bike.drive();
    }
}
