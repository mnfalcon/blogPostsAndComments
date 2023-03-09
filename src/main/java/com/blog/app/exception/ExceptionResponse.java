package com.blog.app.exception;

import lombok.Data;


@Data
public class ExceptionResponse {

    private int status;
    private String message;
    private String timeStamp;

}
