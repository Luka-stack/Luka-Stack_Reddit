package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/posts")
public interface PostApi {

    @PostMapping
    ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest);

    @GetMapping
    ResponseEntity<List<PostResponse>> getAllPosts();

    @GetMapping("/{id}")
    ResponseEntity<PostResponse> getPost(@PathVariable Long id);

    @GetMapping("/by-subreddit/{id}")
    ResponseEntity<List<PostResponse>> getPostBySubReddit(@PathVariable  Long id);

    @GetMapping("/by-user/{username}")
    ResponseEntity<List<PostResponse>> getPostByUsername(@PathVariable String username);
}
