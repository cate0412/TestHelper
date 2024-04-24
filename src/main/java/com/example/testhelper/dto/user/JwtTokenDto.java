package com.example.testhelper.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenDto {

    private final String accessToken;
    private final String tokenType = "Bearer";
    private final Long expiresIn;

    @Builder
    public JwtTokenDto(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }
}
