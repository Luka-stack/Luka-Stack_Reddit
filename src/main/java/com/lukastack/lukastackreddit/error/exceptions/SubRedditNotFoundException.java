package com.lukastack.lukastackreddit.error.exceptions;

public class SubRedditNotFoundException extends RuntimeException {

    public SubRedditNotFoundException(String message) {
        super(message);
    }
}
