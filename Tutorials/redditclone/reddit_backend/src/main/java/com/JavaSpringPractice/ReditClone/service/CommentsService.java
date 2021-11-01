package com.JavaSpringPractice.ReditClone.service;

import com.JavaSpringPractice.ReditClone.dto.CommentsDto;
import com.JavaSpringPractice.ReditClone.exceptions.PostNotFoundException;
import com.JavaSpringPractice.ReditClone.mapper.CommentMapper;
import com.JavaSpringPractice.ReditClone.model.Comment;
import com.JavaSpringPractice.ReditClone.model.NotificationEmail;
import com.JavaSpringPractice.ReditClone.model.Post;
import com.JavaSpringPractice.ReditClone.model.User;
import com.JavaSpringPractice.ReditClone.repository.CommentRepository;
import com.JavaSpringPractice.ReditClone.repository.PostRepository;
import com.JavaSpringPractice.ReditClone.repository.UserRepository;
import com.JavaSpringPractice.ReditClone.service.authorization.AuthService;
import com.JavaSpringPractice.ReditClone.service.mailservice.MailContentBuilder;
import com.JavaSpringPractice.ReditClone.service.mailservice.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto){
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(()->new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto,post,authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername()+" posted a comment on your post"+ post.getUrl());
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user){
        mailService.sendMail(
                new NotificationEmail(
                        user.getUsername()+" Commented to your post",
                        user.getEmail(),
                        message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post =postRepository.findById(postId)
                .orElseThrow(()->new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(()-> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
