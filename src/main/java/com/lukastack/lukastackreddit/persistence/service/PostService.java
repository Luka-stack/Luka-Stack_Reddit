package com.lukastack.lukastackreddit.persistence.service;

import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import com.lukastack.lukastackreddit.error.exceptions.PostNotFoundException;
import com.lukastack.lukastackreddit.error.exceptions.SubRedditNotFoundException;
import com.lukastack.lukastackreddit.mapper.PostMapper;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import com.lukastack.lukastackreddit.persistence.repository.PostRepository;
import com.lukastack.lukastackreddit.persistence.repository.SubRedditRepository;
import com.lukastack.lukastackreddit.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SubRedditRepository subRedditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {

        SubRedditEntity subReddit = subRedditRepository.findByName(postRequest.getSubRedditName()).orElseThrow(
                () -> new SubRedditNotFoundException("SubReddit '"+ postRequest.getSubRedditName() +"' not found"));
        postRepository.save(postMapper.mapToPost(postRequest, subReddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {

        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {

        PostEntity post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found"));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostBySubReddit(Long id) {

        SubRedditEntity subReddit = subRedditRepository.findById(id).orElseThrow(
                () -> new SubRedditNotFoundException("SubReddit not found"));
        List<PostEntity> posts = postRepository.findAllBySubReddit(subReddit);

        return posts.stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username '"+ username +"' not found"));

        return postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
