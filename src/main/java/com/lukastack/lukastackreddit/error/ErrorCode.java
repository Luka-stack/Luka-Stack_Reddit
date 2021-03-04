package com.lukastack.lukastackreddit.error;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_VERIFICATION_TOKEN(1001),
    INVALID_REFRESH_TOKEN(1002),
    USER_NOT_EXISTING(1003),
    MAIL_ERROR(1004),
    AUTH_ERROR(1005),
    COMMENT_NOT_FOUND(2000),
    POST_NOT_FOUND(3000),
    SUBREDDIT_NOT_FOUND(4000),
    VOTE_ERROR(5000);

    final int statusCode;

}
