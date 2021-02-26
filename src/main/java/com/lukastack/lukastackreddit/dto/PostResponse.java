package com.lukastack.lukastackreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Long postId;

    private String postName;

    private String url;

    private String description;

    private String userName;

    private String subRedditName;
}
