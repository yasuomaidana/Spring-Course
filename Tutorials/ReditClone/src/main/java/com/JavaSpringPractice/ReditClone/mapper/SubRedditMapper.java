package com.JavaSpringPractice.ReditClone.mapper;

import com.JavaSpringPractice.ReditClone.dto.SubRedditDto;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  SubRedditMapper {
     @Mapping(target = "numberOfPosts",expression = "java(mapPosts(subReddit.getPosts()))")
     @Mapping(source = "name", target = "subRedditName")
     SubRedditDto mapSubRedditToDto(SubReddit subReddit);
     default Integer mapPosts(List<Post> numberOfPosts){return numberOfPosts.size();}

     @InheritInverseConfiguration
     @Mapping(target = "posts",ignore = true)
     SubReddit mapDtoToSubReddit(SubRedditDto subRedditDto);
}
