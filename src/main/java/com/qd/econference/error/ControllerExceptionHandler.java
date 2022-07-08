package com.qd.econference.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
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

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleIllegalArgumentException(NoSuchElementException ex) {
        String msg = "Element not found: " + ex.getMessage();
        log.info(msg);
        return ErrorDto.builder().requestId(UUID.randomUUID()).message(msg).build();
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleDuplicateKeyException(DuplicateKeyException ex) {
        String msg = "Element already exists: " + ex.getMessage();
        log.info(msg);
        return ErrorDto.builder().requestId(UUID.randomUUID()).message(msg).build();
    }
}
