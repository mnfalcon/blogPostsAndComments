package com.blog.app.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException exc) {
        log.info(exc.getMessage());
        ExceptionResponse e = createExceptionResponse(exc.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionResponse> notFoundException(MethodArgumentTypeMismatchException exc) {
        log.info(exc.getMessage());
        ExceptionResponse e = createExceptionResponse("Bad request", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse createExceptionResponse(String message, HttpStatus status) {
        ExceptionResponse e = new ExceptionResponse();

        e.setStatus(status.value());
        e.setMessage(message);
        e.setTimeStamp(LocalDateTime.now().toString());

        return e;
    }

}
