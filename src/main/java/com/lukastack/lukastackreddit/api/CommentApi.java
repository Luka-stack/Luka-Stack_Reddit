package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
public interface CommentApi {

    @PostMapping
    ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto);

    @GetMapping
    ResponseEntity<List<CommentDto>> getAllCommentsByPost(@RequestParam Long postId);

    @GetMapping
    ResponseEntity<List<CommentDto>> getAllCommentByUser(@RequestParam String username);
}
