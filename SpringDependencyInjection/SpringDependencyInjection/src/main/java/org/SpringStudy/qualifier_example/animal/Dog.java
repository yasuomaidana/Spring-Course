package org.SpringStudy.qualifier_example.animal;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal{

    @Override
    public String name() {
        return "Dog";
    }
}
