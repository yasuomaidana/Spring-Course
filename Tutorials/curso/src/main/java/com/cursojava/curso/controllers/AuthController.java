package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UserDao;
import com.cursojava.curso.models.User;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User verifiedUser = userDao.getVerifiedUser(user);
        if(verifiedUser!=null) {
            //returns token
            return jwtUtil.createToken(String.valueOf(verifiedUser.getPassword()),verifiedUser.getEmail());
        }
        else return "FAIL";
    }
}
