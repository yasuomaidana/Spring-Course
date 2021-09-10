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

    //Advanced requests
    //Register a user
    @RequestMapping(value = "api/user",method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        userDao.register(user);
    }
    //Returns a user using ID
    @RequestMapping(value="api/user/{ID}")
    public User getUserByID(@PathVariable Long ID){
        return userDao.getUserById(ID);
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

}
