package com.example.simplewebapplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Ready for use Spring embassy to handle web request
public class SimpleController {
    @RequestMapping("/")
    public String index(){
        return "Hello world";
    }
    @RequestMapping("/2")
    public String index2(){
        return "The idea works";
    }
}
