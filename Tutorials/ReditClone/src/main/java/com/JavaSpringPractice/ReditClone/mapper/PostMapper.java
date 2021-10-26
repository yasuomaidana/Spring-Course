package com.JavaSpringPractice.ReditClone.mapper;

import com.JavaSpringPractice.ReditClone.dto.PostRequest;
import com.JavaSpringPractice.ReditClone.dto.PostResponse;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface PostMapper {
    @Mapping(target = "createdDate",expression ="java(java.time.Instant.now())")
    @Mapping(target = "description",source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "voteCount", constant = "0")
    Post map(PostRequest postRequest, SubReddit subReddit, User user);

    @Mapping(target="id",source="postId")
    @Mapping(target="subRedditName",source="subReddit.name")
    @Mapping(target="userName",source="user.username")
    PostResponse mapToDto(Post post);
}
