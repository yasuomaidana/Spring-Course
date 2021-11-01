package com.JavaSpringPractice.ReditClone.exceptions;

public class SubRedditNotFoundException extends RuntimeException{
    public SubRedditNotFoundException(String message){
        super(message);
    }
}
