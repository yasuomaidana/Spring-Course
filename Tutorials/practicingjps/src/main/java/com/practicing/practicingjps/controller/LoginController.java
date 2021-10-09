package com.practicing.practicingjps.controller;

import com.practicing.practicingjps.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(){
        return "loginForm";
    }
    @PostMapping
    public String loginForm(Model model, UserDto userDto){
        model.addAttribute("username",userDto.getUsername());
        model.addAttribute("password",userDto.getPassword());
        model.addAttribute(userDto);
        return "menu";
    }
}
