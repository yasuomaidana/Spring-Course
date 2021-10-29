package com.JavaSpringPractice.ReditClone.controller;

import com.JavaSpringPractice.ReditClone.dto.AuthenticationResponse;
import com.JavaSpringPractice.ReditClone.dto.LoginRequest;
import com.JavaSpringPractice.ReditClone.dto.RefreshTokenRequest;
import com.JavaSpringPractice.ReditClone.dto.RegisterRequest;
import com.JavaSpringPractice.ReditClone.service.authorization.AuthService;
import com.JavaSpringPractice.ReditClone.service.authorization.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration Successful", OK);
    }
    @GetMapping("accountVerification/{token}")
    public  ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return status(OK).body("Refresh token deleted successfully");
    }
}
