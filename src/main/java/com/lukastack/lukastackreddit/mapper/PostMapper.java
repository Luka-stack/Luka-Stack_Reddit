package com.lukastack.lukastackreddit.mapper;

import com.lukastack.lukastackreddit.dto.PostRequest;
import com.lukastack.lukastackreddit.dto.PostResponse;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    PostEntity mapToPost(PostRequest postRequest, SubRedditEntity subReddit, UserEntity user);

    @Mapping(target = "subRedditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(PostEntity post);
}
