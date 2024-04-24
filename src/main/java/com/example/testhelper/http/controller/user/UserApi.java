package com.example.testhelper.http.controller.user;

import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.user.UserRequestDto;
import com.example.testhelper.dto.user.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "사용자 정보 관련")
public interface UserApi {

    @Operation(summary = "회원 정보 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
    })
    UserResponseDto.UserInfo getUserById(@Parameter(description = "유저 ID", required = true)
                                         @PathVariable int id
    );

    @Operation(summary = "회원 정보 수정")
    UserResponseDto.UserInfo updateUser(
            User user,
            @Parameter(description = "유저 ID", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody UserRequestDto.Update requestDto);

    @Operation(summary = "회원가입")
    UserResponseDto.UserInfo createUser(@Valid @RequestBody UserRequestDto.Create requestDto);

    @Operation(summary = "회원 탈퇴")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json")
    })
    Integer withdrawUser(
            User user,
            @Parameter(description = "유저 ID", required = true)
            @PathVariable Integer id
    );

    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json")
    })

    @Operation(summary = "회원비밀번호 수정")
    Integer updateUserPassword(
            User user,
            @Parameter(description = "유저 ID", required = true)
            @PathVariable Integer id,
            @Valid @RequestBody UserRequestDto.PasswordUpdate requestDto);


}
