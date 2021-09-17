package org.SpringStudy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//Looks for the package where the Services use components to link
@ComponentScan(basePackages = {"org.SpringStudy.setters","org.SpringStudy.vehicle"})
public class Config2 {
}
