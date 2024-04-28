package com.example.testhelper.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TestResultStatusEnum implements CommonCodeEnum {

    END("TSST_END"),
    NOT_START("TSST_NOT_START");

    public final String code;

    @Override
    public String code() {
        return this.code;
    }
}
