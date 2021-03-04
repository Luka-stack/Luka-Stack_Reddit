package com.lukastack.lukastackreddit.error.exceptions;

import com.lukastack.lukastackreddit.error.ErrorCode;

public class SubRedditNotFoundException extends CustomServiceException {

    public SubRedditNotFoundException(String message) {
        super(message, ErrorCode.SUBREDDIT_NOT_FOUND);
    }
}
