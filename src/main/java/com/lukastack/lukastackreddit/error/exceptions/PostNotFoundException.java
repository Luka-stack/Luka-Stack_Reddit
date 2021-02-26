package com.lukastack.lukastackreddit.error.exceptions;

import org.hibernate.service.spi.ServiceException;

public class PostNotFoundException  extends ServiceException {

    public PostNotFoundException(String message) {
        super(message);
    }
}
