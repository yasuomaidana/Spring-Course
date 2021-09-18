package org.SpringStudy;

import org.SpringStudy.qualifier_example.animal.Animal;
import org.SpringStudy.qualifier_example.animal.Cat;
import org.SpringStudy.qualifier_example.animal.Dog;
import org.SpringStudy.qualifier_example.animal.PetShow;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
public class SpringBeanNamingUnitTest {
    private AnnotationConfigApplicationContext context;
    @Test
    public void multipleImplementationOfAnimals_usingQualifiers(){
        context = new AnnotationConfigApplicationContext(org.SpringStudy.qualifier_example.Config.class);
        PetShow petShow = (PetShow) context.getBean("petShow");
        assertThat(petShow.getCat(), CoreMatchers.<Animal>instanceOf(Cat.class));
        assertThat(petShow.getDog(),CoreMatchers.<Animal>instanceOf(Dog.class));
        System.out.println(petShow.getCat().name());
        System.out.println(petShow.getDog().name());
    }

}
