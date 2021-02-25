package com.lukastack.lukastackreddit.persistence.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VoteType {
    UP_VOTE(1), DOWN_VOTE(-1);

    final int vote;
}
