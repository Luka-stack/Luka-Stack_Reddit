package com.lukastack.lukastackreddit.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Error {

    private final String message;

    private final ErrorCode errorCode;
}
