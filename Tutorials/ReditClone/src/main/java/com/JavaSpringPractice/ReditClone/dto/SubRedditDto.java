package com.JavaSpringPractice.ReditClone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SubRedditDto {
    private Long id;
    private String subRedditName;
    private String description;
    private Integer numberOfPosts;
}
