package org.SpringStudy.qualifier_example.animal;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PetShow {
    @Getter
    private final Animal dog;
    @Getter
    private final Animal cat;
    public PetShow(@Qualifier("dog") Animal dog,@Qualifier("cat") Animal cat){
        this.dog = dog;
        this.cat = cat;
    }
}
