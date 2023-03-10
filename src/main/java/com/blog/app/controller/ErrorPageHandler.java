package com.blog.app.controller;

import com.blog.app.exception.ExceptionResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ErrorPageHandler implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ExceptionResponse> error() {
        ExceptionResponse res = ExceptionResponse.builder()
                .message("Page not found")
                .status(404)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

    }
}
