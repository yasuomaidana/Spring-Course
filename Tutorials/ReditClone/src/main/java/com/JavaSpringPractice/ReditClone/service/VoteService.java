package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.VoteDto;
import com.JavaSpringPractice.ReditClone.exceptions.PostNotFoundException;
import com.JavaSpringPractice.ReditClone.exceptions.SpringRedditException;
import com.JavaSpringPractice.ReditClone.mapper.VoteMapper;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.vote.Vote;
import com.JavaSpringPractice.ReditClone.repository.PostRepository;
import com.JavaSpringPractice.ReditClone.repository.VoteRepository;
import com.JavaSpringPractice.ReditClone.service.authorization.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.JavaSpringPractice.ReditClone.model.vote.VoteType.UPVOTE;

@Transactional
@Service
@AllArgsConstructor @Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final VoteMapper voteMapper;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(()->new PostNotFoundException(voteDto.getPostId().toString()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
        if(voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get()
                        .getVoteType().equals(voteDto.getVoteType())){
            throw new SpringRedditException("You have already "
                    +voteDto.getVoteType()+" 'd for this post");
        }
        if(UPVOTE.equals(voteDto.getVoteType())){post.setVoteCount(post.getVoteCount()+1);}
        else{post.setVoteCount(post.getVoteCount()-1);}
        voteRepository.save(voteMapper.map(voteDto,post,authService.getCurrentUser()));
        postRepository.save(post);
    }
}
