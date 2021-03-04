package com.lukastack.lukastackreddit.error.exceptions;

import com.lukastack.lukastackreddit.error.ErrorCode;

public class PostNotFoundException  extends CustomServiceException {

    public PostNotFoundException(String message) {
        super(message, ErrorCode.POST_NOT_FOUND);
    }
}
