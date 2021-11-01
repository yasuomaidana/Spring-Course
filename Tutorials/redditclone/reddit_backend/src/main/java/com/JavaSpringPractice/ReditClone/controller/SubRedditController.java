package com.JavaSpringPractice.ReditClone.controller;

import com.JavaSpringPractice.ReditClone.dto.SubRedditDto;
import com.JavaSpringPractice.ReditClone.service.SubRedditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubRedditController {
    private final SubRedditService subRedditService;
    @PostMapping
    public ResponseEntity<SubRedditDto> createSubReddit(@RequestBody SubRedditDto subRedditDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subRedditService.save(subRedditDto));
    }
    @GetMapping
    public ResponseEntity<List<SubRedditDto>> getAllSubReddit(){
         return ResponseEntity.status(HttpStatus.OK)
                 .body(subRedditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubRedditDto> getSubReddit(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(subRedditService.getSubRedditId(id));
    }
}
