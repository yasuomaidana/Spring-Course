package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")//Makes reference to the table
public class User {
    //we could have mapped each property using @Column(name="column name")
    //however, given that the properties and column names matches, we don't need to do it
    @Getter @Setter
    @Id
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String phone;
    @Getter @Setter
    private String password;
}
