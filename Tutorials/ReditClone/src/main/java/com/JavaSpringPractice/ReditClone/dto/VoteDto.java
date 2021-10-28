package com.JavaSpringPractice.ReditClone.dto;

import com.JavaSpringPractice.ReditClone.model.vote.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
