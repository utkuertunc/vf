package com.utkuertunc.vf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    Custom exception for full garage
*/
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NoAvailableSlotException extends RuntimeException {

    public NoAvailableSlotException(String exception) {
        super(exception);
    }
}
