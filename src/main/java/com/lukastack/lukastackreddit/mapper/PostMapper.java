package com.lukastack.lukastackreddit.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import com.lukastack.lukastackreddit.persistence.entity.*;
import com.lukastack.lukastackreddit.persistence.repository.CommentRepository;
import com.lukastack.lukastackreddit.persistence.repository.VoteRepository;
import com.lukastack.lukastackreddit.persistence.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract PostEntity mapToPost(PostRequest postRequest, SubRedditEntity subReddit, UserEntity user);

    @Mapping(target = "subRedditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(PostEntity post);

    Integer commentCount(PostEntity post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(PostEntity post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(PostEntity post) {
        return checkVoteType(post, VoteType.UP_VOTE);
    }

    boolean isPostDownVoted(PostEntity post) {
        return checkVoteType(post, VoteType.DOWN_VOTE);
    }

    private boolean checkVoteType(PostEntity post, VoteType voteType) {

        if (authService.isLoggedIn()) {
            Optional<VoteEntity> voteForPostByUser =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }

        return false;
    }
}
