package com.lukastack.lukastackreddit.persistence.service;

import com.lukastack.lukastackreddit.dto.VoteDto;
import com.lukastack.lukastackreddit.error.exceptions.PostNotFoundException;
import com.lukastack.lukastackreddit.error.exceptions.SpringRedditException;
import com.lukastack.lukastackreddit.mapper.VoteMapper;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.VoteEntity;
import com.lukastack.lukastackreddit.persistence.entity.VoteType;
import com.lukastack.lukastackreddit.persistence.repository.PostRepository;
import com.lukastack.lukastackreddit.persistence.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final VoteMapper voteMapper;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {

        PostEntity post = postRepository.findById(voteDto.getPostId()).orElseThrow(
                () -> new PostNotFoundException("Post with ID: "+ voteDto.getPostId() +" not found"));
        Optional<VoteEntity> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(
                post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() &&
            voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "+ voteDto.getVoteType() +"'d for this post");
        }

        if (VoteType.UP_VOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        }
        else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(voteMapper.mapToVote(voteDto, post, authService.getCurrentUser()));
        postRepository.save(post);
    }
}
