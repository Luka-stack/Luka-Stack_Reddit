package com.lukastack.lukastackreddit.api;

import com.lukastack.lukastackreddit.dto.VoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/votes")
public interface VoteApi {

    @PostMapping
    ResponseEntity<VoteDto> vote(@RequestBody VoteDto voteDto);
}
