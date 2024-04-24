package com.example.testhelper.dto.user;

import com.example.testhelper.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class AuthDto {

    @Getter
    public static class LoginRequest {
        @NotBlank
        @Schema(description = "이메일", example = "이메일")
        private String email;

        @NotBlank
        @Schema(description = "비밀번호", example = "비밀번호")
        private String userPassword;


        @Builder
        public LoginRequest(String email, String userPassword) {
            this.email = email;
            this.userPassword = userPassword;
        }
    }

    @Getter
    public static class LoginResponse {
        @Schema(description = "토큰", example = "토큰")
        private String accessToken;

        @Schema(description = "유저 번호", example = "1")
        private Integer id;

        @Schema(description = "이름", example = "이름")
        private String name;

        @Schema(description = "회원 상태", example = "회원 상태코드")
        private String status;

        @Builder
        public LoginResponse(String accessToken, User user) {
            this.accessToken = accessToken;
            this.id = user.getId();
            this.name = user.getName();
            this.status = user.getStatus().getName();
        }
    }
}
