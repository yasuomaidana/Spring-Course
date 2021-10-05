package com.JavaSpringPractice.ReditClone.repository;

import com.JavaSpringPractice.ReditClone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit,Long> {
}
