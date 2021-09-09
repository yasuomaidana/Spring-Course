package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name="users")//Makes reference to the table
public class User {
    //we could have mapped each property using @Column(name="column name")
    //however, given that the properties and column names matches, we don't need to do it
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Used to auto fix the ID
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phone; //It was set as default value null from database
    @Getter @Setter
    private String password;
}
