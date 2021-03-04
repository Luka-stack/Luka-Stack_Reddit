package com.lukastack.lukastackreddit.error.exceptions;

import com.lukastack.lukastackreddit.error.ErrorCode;

public class SpringRedditException extends CustomServiceException {

    public SpringRedditException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
