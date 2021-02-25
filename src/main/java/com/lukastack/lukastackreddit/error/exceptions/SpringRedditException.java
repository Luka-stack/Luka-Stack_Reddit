package com.lukastack.lukastackreddit.error.exceptions;

import org.hibernate.service.spi.ServiceException;

public class SpringRedditException extends ServiceException {

    public SpringRedditException(String message) {
        super(message);
    }
}
