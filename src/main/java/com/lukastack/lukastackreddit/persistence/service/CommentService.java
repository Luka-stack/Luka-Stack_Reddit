package com.lukastack.lukastackreddit.persistence.service;

import com.lukastack.lukastackreddit.dto.CommentDto;
import com.lukastack.lukastackreddit.error.exceptions.PostNotFoundException;
import com.lukastack.lukastackreddit.mapper.CommentMapper;
import com.lukastack.lukastackreddit.model.MailContentBuilder;
import com.lukastack.lukastackreddit.model.NotificationEmail;
import com.lukastack.lukastackreddit.persistence.entity.CommentEntity;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import com.lukastack.lukastackreddit.persistence.repository.CommentRepository;
import com.lukastack.lukastackreddit.persistence.repository.PostRepository;
import com.lukastack.lukastackreddit.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;
    private final CommentMapper commentMapper;

    public void createComment(CommentDto commentDto) {

        PostEntity post = postRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new PostNotFoundException("Post not found"));
        CommentEntity comment = commentMapper.mapToComment(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authService.getCurrentUser() +" posted a comment on your post.");
        sendCommentNotification(message, post.getUser());
    }

    public List<CommentDto> getCommentByPost(Long postId) {

        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException("Post not found"));

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentByUser(String username) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User '"+ username +"' not found"));

        return commentRepository.findByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private void sendCommentNotification(String message, UserEntity user) {
        mailService.sendMail(new NotificationEmail(user.getPassword() +" Commented on your post",
                user.getEmail(), message));
    }
}
