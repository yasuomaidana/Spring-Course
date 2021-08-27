package com.cursojava.curso.controllers;
//Controllers are used to manage urls
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Indicates that it is a controller
public class UsuarioController {
    @RequestMapping(value = "prueba")
    public String prueba(){
         return "prueba";
    }
}
