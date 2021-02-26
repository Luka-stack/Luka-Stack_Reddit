package com.lukastack.lukastackreddit.mapper;

import com.lukastack.lukastackreddit.dto.SubRedditDto;
import com.lukastack.lukastackreddit.persistence.entity.PostEntity;
import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import com.lukastack.lukastackreddit.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubRedditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subReddit.getPosts()))")
    SubRedditDto mapSubRedditToDto(SubRedditEntity subReddit);

    @InheritInverseConfiguration
    @Mapping(target = "posts", source = "subRedditDto", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    SubRedditEntity mapToSubReddit(SubRedditDto subRedditDto, UserEntity user);

    default Integer mapPosts(List<PostEntity> posts) {
        return posts.size();
    }
}
