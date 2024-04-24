package com.example.testhelper.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserExceptionEnum implements ExceptionEnum{

    USER_EXISTS(HttpStatus.BAD_REQUEST.value(), "The same user already exists."),
    WITHDRAW_USER(HttpStatus.UNAUTHORIZED.value(), "This user is withdraw.");

    private final Integer status;
    private final String message;

    @Override
    public Integer status() {
        return null;
    }

    @Override
    public String code() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }
}
