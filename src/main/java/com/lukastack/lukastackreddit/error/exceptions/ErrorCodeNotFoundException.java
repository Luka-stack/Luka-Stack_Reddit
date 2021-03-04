package com.lukastack.lukastackreddit.error.exceptions;

import org.hibernate.service.spi.ServiceException;

public class ErrorCodeNotFoundException extends ServiceException {

    public ErrorCodeNotFoundException(String message) {
        super(message);
    }
}
