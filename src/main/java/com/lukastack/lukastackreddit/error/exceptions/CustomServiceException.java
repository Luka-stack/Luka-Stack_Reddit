package com.lukastack.lukastackreddit.error.exceptions;

import com.lukastack.lukastackreddit.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomServiceException(String message, ErrorCode errorCode) {

        super(message);
        this.errorCode = errorCode;
    }
}
