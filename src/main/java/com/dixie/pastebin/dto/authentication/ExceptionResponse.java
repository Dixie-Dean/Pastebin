package com.dixie.pastebin.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private String exception;
    private String inAdviceMessage;
    private String exceptionMessage;
    private int status;
}
