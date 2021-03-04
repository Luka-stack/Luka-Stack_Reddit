package com.lukastack.lukastackreddit.controller;

import com.lukastack.lukastackreddit.api.VoteApi;
import com.lukastack.lukastackreddit.dto.VoteDto;
import com.lukastack.lukastackreddit.persistence.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController implements VoteApi {

    private final VoteService voteService;

    @Override
    public ResponseEntity<VoteDto> vote(VoteDto voteDto) {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.vote(voteDto));
    }
}
