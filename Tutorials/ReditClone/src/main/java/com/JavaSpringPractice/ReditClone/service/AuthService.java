package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.RegisterRequest;
import com.JavaSpringPractice.ReditClone.exceptions.SpringRedditException;
import com.JavaSpringPractice.ReditClone.model.NotificationEmail;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.model.token.VerificationToken;
import com.JavaSpringPractice.ReditClone.repository.UserRepository;
import com.JavaSpringPractice.ReditClone.repository.VerificationTokenRepository;
import com.JavaSpringPractice.ReditClone.service.mailservice.MailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

     private final PasswordEncoder passwordEncoder;//Avoid use infield auto-wire annotation
     private final UserRepository userRepository;
     private final VerificationTokenRepository verificationTokenRepository;
     private final MailService mailService;

     @Transactional
     public void signup(RegisterRequest registerRequest){
          User user = new User();
          user.setUsername(registerRequest.getUsername());
          user.setEmail(registerRequest.getEmail());
          user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
          user.setCreated(Instant.now());
          user.setEnabled(false);
          userRepository.save(user);

          String token = generateVerificationToken(user);
          mailService.sendMail(
                  new NotificationEmail(
                          "Please Activate your Account"
                          ,user.getEmail(),
                          "Thank you for signing up to Spring Reddit, " +
                          "please click on the below url to activate your account : " +
                          "http://localhost:8080/api/auth/accountVerification/" + token));
     }
     private String generateVerificationToken(User user){
          String token = UUID.randomUUID().toString();
          VerificationToken verificationToken = new VerificationToken();
          verificationToken.setToken(token);
          verificationToken.setUser(user);

          verificationTokenRepository.save(verificationToken);
          return token;
     }
     public void verifyAccount(String token){
          fetchUserAndEnable(
                  verificationTokenRepository
                          .findByToken(token)
                          .orElseThrow(()->new SpringRedditException("Invalid token")));
     }
     @Transactional
     private void fetchUserAndEnable(VerificationToken verificationToken){
          String email = verificationToken.getUser().getEmail();
          User user = userRepository.findByEmail(email)
                  .orElseThrow(()->new SpringRedditException("User not found"));
          user.setEnabled(true);
          userRepository.save(user);
     }
}
