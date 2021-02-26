package com.lukastack.lukastackreddit.controller;

import com.lukastack.lukastackreddit.api.PostApi;
import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import com.lukastack.lukastackreddit.persistence.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController implements PostApi {

    private final PostService postService;

    @Override
    public ResponseEntity<Void> createPost(PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @Override
    public ResponseEntity<PostResponse> getPost(Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @Override
    public ResponseEntity<List<PostResponse>> getPostBySubReddit(Long id) {
        return ResponseEntity.ok(postService.getPostBySubReddit(id));
    }

    @Override
    public ResponseEntity<List<PostResponse>> getPostByUsername(String username) {
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }
}
