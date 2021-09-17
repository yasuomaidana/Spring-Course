package org.SpringStudy.setters;

import org.SpringStudy.vehicle.Bike;
import org.SpringStudy.vehicle.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsingSetters{
    Car car;
    Bike bike;
    @Autowired
    UsingSetters(Car car, Bike bike){
        this.car=car;
        this.bike=bike;
    }
    public void driveAll(){
        car.drive();
        bike.drive();
    }
}