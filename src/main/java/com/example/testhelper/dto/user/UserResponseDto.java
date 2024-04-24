package com.example.testhelper.dto.user;

import com.example.testhelper.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class UserResponseDto {

    @Getter
    public static class UserInfo {
        @Schema(description = "유저 ID")
        private Integer id;

        @Schema(description = "유저 이메일", example = "qwerty@email.com")
        private String email;

        @Schema(description = "이름", example = "이름")
        private String name;

        @Schema(description = "성별", example = "성별")
        private String gender;

        @Schema(description = "유저 상태코드", example = "유저 상태코드")
        private String status;

        public UserInfo(User entity) {
            this.id = entity.getId();
            this.email = entity.getEmail();
            this.name = entity.getName();
            this.gender = entity.getGender().name();
            this.status = entity.getStatus().getName();

        }
    }
}
