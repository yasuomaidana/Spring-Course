package com.JavaSpringPractice.ReditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    @NotBlank(message = "User Name is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email @Column(unique = true)
    @NotBlank(message = "Email is required")
    private String email;
    private Instant created;
    private boolean enabled;
}
