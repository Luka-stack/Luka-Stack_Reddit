package com.lukastack.lukastackreddit.controller;

import com.lukastack.lukastackreddit.api.SubRedditApi;
import com.lukastack.lukastackreddit.dto.SubRedditDto;
import com.lukastack.lukastackreddit.persistence.service.SubRedditService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubRedditController implements SubRedditApi {

    private final SubRedditService subRedditService;

    @Override
    public ResponseEntity<List<SubRedditDto>> getAllSubReddits() {

        return ResponseEntity.ok(subRedditService.getAllSubReddits());
    }

    @Override
    public ResponseEntity<SubRedditDto> getSubReddit(Long id) {

        return ResponseEntity.ok(subRedditService.getSubReddit(id));
    }

    @Override
    public ResponseEntity<SubRedditDto> createSubReddit(@Valid SubRedditDto subRedditDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subRedditService.save(subRedditDTO));
    }


}
