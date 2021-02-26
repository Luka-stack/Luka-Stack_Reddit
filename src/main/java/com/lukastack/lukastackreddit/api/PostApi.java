package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
public interface PostApi {

    // TODO Return Body
    @PostMapping
    ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest);

    @GetMapping
    ResponseEntity<List<PostResponse>> getAllPosts();

    @GetMapping("/{id}")
    ResponseEntity<PostResponse> getPost(@PathVariable Long id);

    @GetMapping("/by-subreddit/{id}")
    ResponseEntity<List<PostResponse>> getPostBySubReddit(Long id);

    @GetMapping("/by-user/{name}")
    ResponseEntity<List<PostResponse>> getPostByUsername(String username);
}
