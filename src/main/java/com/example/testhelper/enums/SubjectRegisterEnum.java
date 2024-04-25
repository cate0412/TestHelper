package com.example.testhelper.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubjectRegisterEnum implements CommonCodeEnum{
    ONGOING("SJST_ONGOING"),
    END("SJST_END"),
    NOT_PAID("SJST_NOT_PAID"),
    CANCEL("SJST_CANCEL"),
    FAIL("SJST_FAIL");

    public final String code;

    @Override
    public String code() {
        return this.code;
    }
}
