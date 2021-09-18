# Dependency injection using Spring
This project was done following this [video](https://youtu.be/If1Lw4pLLEo?t=1406).  
Additionally, I make some code from [here](https://www.baeldung.com/spring-bean-names). 

Part of the core applications lives inside App.class.

## Instantiation using spring
In the first part you can see how we can instantiate and use Java objects following the traditional way.However, just below I show how to instantiate objects using Spring.
```java
public class App
{
    public static void main( String[] args )
    {
        //Using Spring Context
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Car carSpring = context.getBean(Car.class);
        Bike bikeSpring = context.getBean(Bike.class);
        carSpring.drive();
        bikeSpring.drive();
        
    }
}
```
Here you can see that the first thing I do is to create an ApplicationContext, this is the Spring beans container. 
There we have the information about the things that we are creating, because as you can see, we are not using ``new`` keyword to define the objects. 
Inside Config class you must find the beans' declaration, or at least define where to search for the beans

```java
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
```
In this configuration class you can see how we are defining the Beans using its notation.

Once defined the Config class, we can load the object using ```context.getBean()```  
