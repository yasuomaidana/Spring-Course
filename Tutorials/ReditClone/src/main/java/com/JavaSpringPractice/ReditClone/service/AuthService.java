package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.RegisterRequest;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

     private final PasswordEncoder passwordEncoder;//Avoid use infield auto-wire annotation
     private final UserRepository userRepository;

     @Transactional
     public void signup(RegisterRequest registerRequest){
          User user = new User();
          user.setUsername(registerRequest.getUsername());
          user.setEmail(registerRequest.getEmail());
          user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
          user.setCreated(Instant.now());
          user.setEnabled(false);
          userRepository.save(user);

          generateVerificationToken(user);
     }
     private void generateVerificationToken(User user){
          String verificationToken = UUID.randomUUID().toString();

     }
}
