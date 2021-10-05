package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.SubRedditDto;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor @Slf4j
public class SubRedditService {
    private final SubRedditRepository subRedditRepository;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto){
        SubReddit saved = subRedditRepository.save(mapSubRedditDto(subRedditDto));
        subRedditDto.setId(saved.getId());
        return subRedditDto;
    }

    @Transactional(readOnly=true)
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll().stream()
                .map(this::mapToDto).collect(toList());
    }

    private SubRedditDto mapToDto(SubReddit subReddit) {
        return SubRedditDto.builder().subRedditName(subReddit.getName())
                .id(subReddit.getId())
                .numberOfPosts(subReddit.getPosts().size())
                .description(subReddit.getDescription())
                .build();
    }

    private SubReddit mapSubRedditDto(SubRedditDto subRedditDto) {
        return SubReddit.builder().name(subRedditDto.getSubRedditName())
                .description(subRedditDto.getDescription())
                .build();
    }

}
