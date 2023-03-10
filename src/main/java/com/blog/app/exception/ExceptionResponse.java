package com.blog.app.exception;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ExceptionResponse {

    private int status;
    private String message;
    private String timeStamp;

}
