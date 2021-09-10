package com.cursojava.curso.controllers;

import com.cursojava.curso.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PracticeController {
    //Raw proofs
    @RequestMapping(value = "api/proof")
    public String proof(){
        return "proof";
    }

    //Returns an user
    @RequestMapping(value = "api/proofUser")
    public User userProof(){
        User user = new User();
        user.setEmail("idoncare@LOL.com");
        user.setName("Who");
        user.setLastname("Dr");
        user.setPhone("1234567890");
        return  user;
    }
    private User createUser(Long Id,String Name,String Lastname,String Phone){
        User user = new User();
        user.setId(Id);
        user.setName(Name);
        user.setLastname(Lastname);
        user.setEmail(Name+"_"+Lastname+"@idc.idk");
        user.setPhone(Phone);
        return user;
    }
    @RequestMapping(value="api/usersHard")
    public List<User> getUsersHard(){
        List<User> users = new ArrayList<>();
        users.add(createUser(123L,"John","Black","1234"));
        users.add(createUser(124L,"Mike","Wasabi","1235"));
        users.add(createUser(125L,"Buzz","Lightyear","1236"));
        return users;
    }
}
