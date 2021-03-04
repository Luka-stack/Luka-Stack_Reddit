package com.lukastack.lukastackreddit.dto;

import com.lukastack.lukastackreddit.persistence.entity.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {

    private VoteType voteType;

    private Long postId;
}
