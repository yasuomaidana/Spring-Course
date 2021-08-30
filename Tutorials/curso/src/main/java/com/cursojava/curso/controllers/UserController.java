package com.cursojava.curso.controllers;
//Controllers are used to manage urls
import models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Indicates that it is a controller
public class UserController {
    @RequestMapping(value = "proof")
    public String proof(){
         return "proof";
    }
    //Returns an user
    @RequestMapping(value = "proofUser")
    public User userProof(){
        User user = new User();
        user.setEmail("idoncare@LOL.com");
        user.setName("Who");
        user.setLastname("Dr");
        user.setPhone("1234567890");
        return  user;
    }
    //Returns a user using ID
    @RequestMapping(value="user/{ID}")
    public User getUserbyID(@PathVariable Long ID){
        User user = new User();
        user.setId(ID);
        user.setEmail("idoncare@LOL.com");
        user.setName("Who");
        user.setLastname("Dr");
        user.setPhone("1234567890");
        return user;
    }
}
