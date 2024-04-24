package com.example.testhelper.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserStatusEnum implements CommonCodeEnum{

    JOINED("UST_JOINED"),
    WITHDRAW("UST_WITHDRAW");

    public final String code;

    @Override
    public String code() {
        return this.code;
    }
}
