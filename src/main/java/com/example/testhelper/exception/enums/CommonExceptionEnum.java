package com.example.testhelper.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonExceptionEnum implements ExceptionEnum {
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found"),
    AUTHORIZATION_FAILED(HttpStatus.UNAUTHORIZED.value(), "Unauthenticated.");

    private final Integer status;
    private final String message;

    @Override
    public Integer status() {
        return status;
    }

    @Override
    public String code() {
        return name();
    }

    @Override
    public String message() {
        return message;
    }
}
