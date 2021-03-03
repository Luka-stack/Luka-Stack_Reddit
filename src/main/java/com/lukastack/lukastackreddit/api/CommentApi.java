package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
public interface CommentApi {

    @PostMapping
    ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto);

    @GetMapping("/by-post/{postId}")
    ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable Long postId);

    @GetMapping("/by-user/{username}")
    ResponseEntity<List<CommentDto>> getAllCommentByUser(@PathVariable String username);
}
