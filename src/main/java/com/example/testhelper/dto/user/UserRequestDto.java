package com.example.testhelper.dto.user;

import com.example.testhelper.enums.UserGender;
import com.example.testhelper.enums.UserStatusEnum;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class Create {
        @Schema(description = "이메일", example = "1234@email.com")
        private String email;
        @Schema(description = "비밀번호", example = "1234")
        private String userPassword;
        @Schema(description = "이름", example = "홍길동")
        private String name;
        @Schema(description = "성별", example = "M")
        private UserGender gender;

        @Hidden
        private String status;

        @Builder
        public Create(String email, String userPassword, String name, String gender) {
            this.email = email;
            this.userPassword = userPassword;
            this.name = name;
            this.gender = UserGender.valueOf(gender);
            this.status = UserStatusEnum.JOINED.code();

        }
    }

    @Getter
    public static class Update {

        @NotBlank
        @Schema(description = "이메일", example = "qwerty@email.com")
        private String email;

        @NotBlank
        @Schema(description = "유저 이름", example = "유저 이름")
        private String name;

        @Schema(description = "성별", example = "M")
        private String gender;


        @Builder
        public Update(String email, String name, String gender) {
            this.email = email;
            this.name = name;
            this.gender = gender;
        }
    }
    @Getter
    public static class PasswordUpdate {

        @NotBlank
        @Schema(description = "이메일", example = "qwerty@email.com")
        private String email;

        @NotBlank
        @Schema(description = "유저 이름", example = "유저 이름")
        private String name;

        @NotBlank
        @Schema(description = "비밀번호", example = "비밀번호")
        private String userPassword;

        @Builder
        public PasswordUpdate(String email, String name, String userPassword) {
            this.email = email;
            this.name = name;
            this.userPassword = userPassword;
        }
    }



}
