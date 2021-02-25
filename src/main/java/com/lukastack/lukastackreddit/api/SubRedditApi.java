package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.SubRedditDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/subreddit")
public interface SubRedditApi {

    @GetMapping
    ResponseEntity<List<SubRedditDto>> getAllSubReddits();

    @GetMapping("/{id}")
    ResponseEntity<SubRedditDto> getSubReddit(@PathVariable Long id);

    @PostMapping
    ResponseEntity<SubRedditDto> createSubReddit(@RequestBody @Valid SubRedditDto subRedditDTO);
}
