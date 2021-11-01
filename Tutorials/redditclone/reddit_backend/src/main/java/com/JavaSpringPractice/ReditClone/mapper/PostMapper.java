package com.JavaSpringPractice.ReditClone.mapper;

import com.JavaSpringPractice.ReditClone.dto.PostRequest;
import com.JavaSpringPractice.ReditClone.dto.PostResponse;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.repository.CommentRepository;
import com.JavaSpringPractice.ReditClone.repository.VoteRepository;
import com.JavaSpringPractice.ReditClone.service.authorization.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel="spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate",expression ="java(java.time.Instant.now())")
    @Mapping(target = "description",source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "voteCount", constant = "0")
    public  abstract  Post map(PostRequest postRequest, SubReddit subReddit, User user);

    @Mapping(target="id",source="postId")
    @Mapping(target="subRedditName",source="subReddit.name")
    @Mapping(target="userName",source="user.username")
    @Mapping(target="commentCount", expression="java(commentCount(post))")
    @Mapping(target="duration", expression ="java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){return commentRepository.findByPost(post).size();}
    String getDuration(Post post){return TimeAgo.using(post.getCreatedDate().toEpochMilli()).toString();}

}
