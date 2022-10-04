package com.utkuertunc.vf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
    Custom exception for already not parked vehicles
*/
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class AlreadyNotParkingException extends RuntimeException {

    public AlreadyNotParkingException(String exception) {
        super(exception);
    }
}
