package com.JavaSpringPractice.ReditClone.mapper;

import com.JavaSpringPractice.ReditClone.dto.VoteDto;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.model.vote.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {
    @Mapping(target = "voteType",expression = "java(voteDto.getVoteType())")
    @Mapping(target = "post",source = "post")
    @Mapping(target = "user",source = "user")
    Vote map(VoteDto voteDto, Post post, User user);
}
