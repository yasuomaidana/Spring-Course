package com.JavaSpringPractice.ReditClone.controller;

import com.JavaSpringPractice.ReditClone.dto.AuthenticationResponse;
import com.JavaSpringPractice.ReditClone.dto.LoginRequest;
import com.JavaSpringPractice.ReditClone.dto.RegisterRequest;
import com.JavaSpringPractice.ReditClone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration Successful", HttpStatus.OK);
    }
    @GetMapping("accountVerification/{token}")
    public  ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully",HttpStatus.OK);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
