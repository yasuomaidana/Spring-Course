package com.cursojava.curso.controllers;
//Controllers are used to manage urls
import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController//Indicates that it is a controller
public class UserController {

    @Autowired
    private UserDao userDao;
    //Logger created to watch errors
    private Logger logger = LoggerFactory.getLogger(UserController.class);

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

    //Advanced requests
    //Register a user
    @RequestMapping(value = "api/user",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        userDao.register(user);
    }
    //Returns a user using ID
    @RequestMapping(value="api/user/{ID}")
    public User getUserbyID(@PathVariable Long ID){
        User user = new User();
        user.setId(ID);
        user.setEmail("idoncare@LOL.com");
        user.setName("Who");
        user.setLastname("Dr");
        user.setPhone("1234567890");
        return user;
    }
    @RequestMapping(value="api/user/{ID}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long ID){
        userDao.delete(ID);
    }
    @RequestMapping(value="api/users")
    public List<User> getUsers(){
        try{
            return userDao.getUsers();
        }catch(Exception ex){
            logger.error("Error",ex);
            throw ex;
        }
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
