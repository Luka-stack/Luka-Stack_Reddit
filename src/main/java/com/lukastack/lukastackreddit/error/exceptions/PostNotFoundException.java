package com.lukastack.lukastackreddit.error.exceptions;

public class PostNotFoundException  extends RuntimeException {

    public PostNotFoundException(String message) {
        super(message);
    }
}
