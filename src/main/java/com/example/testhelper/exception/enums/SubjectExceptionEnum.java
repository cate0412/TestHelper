package com.example.testhelper.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubjectExceptionEnum implements ExceptionEnum {

    NO_PURCHASE(435, "There is no purchase record."),
    USAGE_PERIOD_INVALID(435, "Cannot use product now."),
    ONGOING_SUBJECT(435, "The same subject is ongoing.");

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
