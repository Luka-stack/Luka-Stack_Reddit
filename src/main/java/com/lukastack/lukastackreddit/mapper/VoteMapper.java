package com.lukastack.lukastackreddit.mapper;

import com.lukastack.lukastackreddit.dto.VoteDto;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import com.lukastack.lukastackreddit.persistence.entity.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", source = "post")
    @Mapping(target = "voteType", source = "voteDto.voteType")
    @Mapping(target = "user", source = "user")
    VoteEntity mapToVote(VoteDto voteDto, PostEntity post, UserEntity user);
}
