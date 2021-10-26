package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.SubRedditDto;
import com.JavaSpringPractice.ReditClone.mapper.SubRedditMapper;
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
    private final SubRedditMapper subRedditMapper;
    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto){
        SubReddit saved = subRedditRepository.save(subRedditMapper.mapDtoToSubReddit(subRedditDto));
        subRedditDto.setId(saved.getId());
        return subRedditDto;
    }

    @Transactional(readOnly=true)
    public List<SubRedditDto> getAll() {
        return subRedditRepository.findAll().stream()
                .map(subRedditMapper::mapSubRedditToDto).collect(toList());
    }

    public SubRedditDto getSubRedditId(Long id){
        SubReddit subReddit = subRedditRepository.getById(id);
        return subRedditMapper.mapSubRedditToDto(subReddit);
    }
}
