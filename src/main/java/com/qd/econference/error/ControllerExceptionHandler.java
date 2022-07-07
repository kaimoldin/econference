package com.qd.econference.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(UsernameNotFoundException ex) {
        return ErrorDto.builder().requestId(UUID.randomUUID()).message("User not found, " + ex.getMessage()).build();
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleNotFoundException(Exception ex) {
        return ErrorDto.builder().requestId(UUID.randomUUID()).message(ex.getMessage()).build();
    }
}
