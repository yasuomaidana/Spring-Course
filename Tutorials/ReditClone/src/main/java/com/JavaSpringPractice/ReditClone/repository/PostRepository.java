package com.JavaSpringPractice.ReditClone.repository;

import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<List<Post>> findAllBySubReddit(SubReddit subReddit);
    Optional<List<Post>> findAllByUser(User user);
}
