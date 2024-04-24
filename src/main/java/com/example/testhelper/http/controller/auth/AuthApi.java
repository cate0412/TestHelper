package com.example.testhelper.http.controller.auth;

import com.example.testhelper.dto.AuthDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Auth", description = "인증")
public interface AuthApi {
    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthDto.LoginResponse.class))
    })
    AuthDto.LoginResponse login(AuthDto.LoginRequest loginRequest);

    @Operation(summary = "테스트 로그인")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AuthDto.LoginResponse.class))
    })
    AuthDto.LoginResponse testLogin();

    @Operation(summary = "로그아웃")
    @ApiResponse(responseCode = "200")
    void logout(HttpServletRequest request);

}
