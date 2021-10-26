package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.PostRequest;
import com.JavaSpringPractice.ReditClone.dto.PostResponse;
import com.JavaSpringPractice.ReditClone.exceptions.SubRedditNotFoundException;
import com.JavaSpringPractice.ReditClone.mapper.PostMapper;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.SubReddit;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.repository.PostRepository;
import com.JavaSpringPractice.ReditClone.repository.SubRedditRepository;
import com.JavaSpringPractice.ReditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service @AllArgsConstructor @Slf4j
public class PostService {

    private final SubRedditRepository subRedditRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save(PostRequest postRequest){
        SubReddit subReddit = subRedditRepository.findByName(postRequest.getSubRedditName())
                .orElseThrow(()-> new SubRedditNotFoundException(postRequest.getSubRedditName()));
        User currentUser = authService.getCurrentUser();
        return postMapper.map(postRequest,subReddit,currentUser);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new SubRedditNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubReddit(Long subRedditId){
        SubReddit subReddit = subRedditRepository.findById(subRedditId)
                .orElseThrow(()->new SubRedditNotFoundException(subRedditId.toString()));
        List<Post> posts = postRepository.findAllBySubReddit(subReddit)
                .orElseThrow(()->new SubRedditNotFoundException(subReddit.toString()));
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        return postRepository.findAllByUser(user)
                .orElseThrow(()->new SubRedditNotFoundException(user.toString()))
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());

    }
}
