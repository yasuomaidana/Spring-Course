package com.JavaSpringPractice.ReditClone.model.token;

import com.JavaSpringPractice.ReditClone.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "token")
public class VerificationToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Instant expireDate;
}
