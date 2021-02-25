package com.lukastack.lukastackreddit.error.exceptions;

import org.hibernate.service.spi.ServiceException;

public class SubRedditNotFoundException extends ServiceException {

    public SubRedditNotFoundException(String message) {
        super(message);
    }
}
