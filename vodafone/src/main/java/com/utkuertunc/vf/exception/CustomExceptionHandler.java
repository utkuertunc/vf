package com.utkuertunc.vf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    Global exception handler for custom exceptions, custom validations and INTERNAL_SERVER_ERROR exceptions
*/
@ControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(NoAvailableSlotException.class)
    public final ResponseEntity<Object> handleNoAvailableSlotException(NoAvailableSlotException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyNotParkingException.class)
    public final ResponseEntity<Object> handleAlreadyNotParkingException(AlreadyNotParkingException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List> validationException(final MethodArgumentNotValidException ex) {
        List list = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }
}
