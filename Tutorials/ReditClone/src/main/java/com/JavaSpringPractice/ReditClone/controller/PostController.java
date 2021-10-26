package com.JavaSpringPractice.ReditClone.controller;

import com.JavaSpringPractice.ReditClone.dto.PostRequest;
import com.JavaSpringPractice.ReditClone.dto.PostResponse;
import com.JavaSpringPractice.ReditClone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest){
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id){
        return status(HttpStatus.OK).body(postService.getPost(id));
    }
}
