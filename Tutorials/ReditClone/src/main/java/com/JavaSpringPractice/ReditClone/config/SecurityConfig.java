package com.JavaSpringPractice.ReditClone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        /*srf is used when are sessions, and we are using cookies to
        authenticate, as REST are stateless, and we are using JSON web tokens for authorization we don't need it*/
        httpSecurity.csrf().disable()
                .authorizeRequests()//Allows incoming requests
                .antMatchers("/api/auth/**")//any other path should from ** should be authenticated
                .permitAll().anyRequest();

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
