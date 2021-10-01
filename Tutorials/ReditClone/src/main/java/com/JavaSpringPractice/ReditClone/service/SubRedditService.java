package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.SubRedditDto;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor @Slf4j
public class SubRedditService {
    private final SubRedditRepository subRedditRepository;

    public SubRedditDto save(SubRedditDto subRedditDto){
        SubReddit subReddit = mapSubRedditDto(subRedditDto);
        SubReddit saved = subRedditRepository.save(subReddit);
        subRedditDto.setId(saved.getId());
        return subRedditDto;
    }

    private SubReddit mapSubRedditDto(SubRedditDto subRedditDto) {
        return SubReddit.builder().name(subRedditDto.getSubRedditName())
                .description(subRedditDto.getDescription())
                .build();
    }
}
