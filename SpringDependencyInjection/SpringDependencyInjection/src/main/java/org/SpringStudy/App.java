package org.SpringStudy;

import org.SpringStudy.setters.NotUsingSetters;
import org.SpringStudy.setters.UsingSetters;
import org.SpringStudy.vehicle.Bike;
import org.SpringStudy.vehicle.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Not using DI
        System.out.println("Not using Spring Context");
        Car car = new Car();
        car.drive();
        Bike bike = new Bike();
        bike.drive();

        //Using Spring Context
        /*
        This approach injects the components from Config
         */
        System.out.println("====== ==== ===== ==== ===");
        System.out.println("Using Spring context");
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Car carSpring = context.getBean(Car.class);
        Bike bikeSpring = context.getBean(Bike.class);
        carSpring.drive();
        bikeSpring.drive();

        //Using dependency injection
        System.out.println("====== ==== ===== ==== ===");
        System.out.println("Using Spring Dependency Injection");
        ApplicationContext context2 = new AnnotationConfigApplicationContext(Config2.class);

        System.out.println("** ** ** **");
        System.out.println("Using Setters");
        UsingSetters usingSetters = context2.getBean(UsingSetters.class);
        usingSetters.driveAll();
        System.out.println("** ** ** **");
        System.out.println("Not using Setters");
        NotUsingSetters notUsingSetters = context2.getBean(NotUsingSetters.class);
        notUsingSetters.driveAll();

        System.out.println("~~~~~~~~~~~");
        System.out.println("Using named beans");
        ApplicationContext usingNames = new AnnotationConfigApplicationContext(ConfigNamedBeans.class);
        Car namedCar = usingNames.getBean("myNamedCar",Car.class);
        namedCar.drive();
    }
}
