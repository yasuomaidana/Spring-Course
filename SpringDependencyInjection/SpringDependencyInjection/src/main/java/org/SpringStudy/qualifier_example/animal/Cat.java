package org.SpringStudy.qualifier_example.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cat") // Spring names its beans using class name in lowercase as default
public class Cat implements Animal{
    @Override
    public String name() {
        return "Cat";
    }
}
