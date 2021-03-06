package com.lukastack.lukastackreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long id;

    private String subRedditName;

    private String postName;

    private String url;

    private String description;
}
