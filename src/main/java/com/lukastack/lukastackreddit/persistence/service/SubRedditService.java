package com.lukastack.lukastackreddit.persistence.service;

import com.lukastack.lukastackreddit.dto.SubRedditDto;
import com.lukastack.lukastackreddit.error.exceptions.SubRedditNotFoundException;
import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import com.lukastack.lukastackreddit.persistence.repository.SubRedditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubRedditService {

    private final SubRedditRepository subRedditRepository;
    private final AuthService authService;

    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {

        SubRedditEntity subReddit = subRedditRepository.save(mapToSubReddit(subRedditDto));
        subRedditDto.setId(subReddit.getSubId());

        return subRedditDto;
    }

    @Transactional(readOnly = true)
    public SubRedditDto getSubReddit(Long id) {

        SubRedditEntity subReddit = subRedditRepository.findById(id).orElseThrow(
                () -> new SubRedditNotFoundException("SubReddit with id #"+ id +" not found"));

        return new SubRedditDto(subReddit);
    }

    @Transactional(readOnly = true)
    public List<SubRedditDto> getAllSubReddits() {

        return subRedditRepository.findAll()
                .stream()
                .map(SubRedditDto::new)
                .collect(toList());
    }

    private SubRedditEntity mapToSubReddit(SubRedditDto subRedditDto) {

        return SubRedditEntity.builder()
                .name("/r/" + subRedditDto.getName())
                .description(subRedditDto.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(now()).build();
    }
}
