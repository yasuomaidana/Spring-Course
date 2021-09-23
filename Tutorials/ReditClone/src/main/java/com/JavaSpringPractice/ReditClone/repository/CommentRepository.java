package com.JavaSpringPractice.ReditClone.repository;

import com.JavaSpringPractice.ReditClone.model.Comment;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    //List<Comment> findByPost(Post post);
    //List<Comment> findAllByUser(User user);
}
