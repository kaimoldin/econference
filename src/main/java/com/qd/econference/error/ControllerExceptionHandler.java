package com.qd.econference.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(UsernameNotFoundException ex) {
        String msg = "User not found, " + ex.getMessage();
        log.info(msg);
        return ErrorDto.builder().requestId(UUID.randomUUID()).message(msg).build();
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleNotFoundException(Exception ex) {
        String msg = ex.getMessage();
        log.info(msg);
        return ErrorDto.builder().requestId(UUID.randomUUID()).message(msg).build();
    }
}
