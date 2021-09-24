package com.JavaSpringPractice.ReditClone.repository;

import com.JavaSpringPractice.ReditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {//Post Entity, Long Type of Primary Key
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
