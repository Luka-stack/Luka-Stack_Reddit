package com.lukastack.lukastackreddit.error;

import com.lukastack.lukastackreddit.error.exceptions.CustomServiceException;
import com.lukastack.lukastackreddit.error.exceptions.PostNotFoundException;
import com.lukastack.lukastackreddit.error.exceptions.SpringRedditException;
import com.lukastack.lukastackreddit.error.exceptions.SubRedditNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { PostNotFoundException.class, SubRedditNotFoundException.class })
    public ResponseEntity<Error> handleNotFound(CustomServiceException e) {

        Error error = new Error(e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SpringRedditException.class )
    public ResponseEntity<Error> handleBadRequest(CustomServiceException e) {

        Error error = new Error(e.getMessage(), e.getErrorCode());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
