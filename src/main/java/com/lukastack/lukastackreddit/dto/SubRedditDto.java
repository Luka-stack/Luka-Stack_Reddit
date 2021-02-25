package com.lukastack.lukastackreddit.dto;

import com.lukastack.lukastackreddit.persistence.entity.SubRedditEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubRedditDto {

    private Long id;
    private String name;
    private String description;
    private Integer postCount;

    public SubRedditDto(SubRedditEntity subReddit) {

        this.id = subReddit.getSubId();
        this.name = subReddit.getName();
        this.description = subReddit.getDescription();
        this.postCount = subReddit.getPosts().size();
    }
}
