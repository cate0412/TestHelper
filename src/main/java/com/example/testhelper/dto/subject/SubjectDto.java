package com.example.testhelper.dto.subject;

import com.example.testhelper.domain.subject.Subject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectDto {
    @Schema(description = "과목 Id", example = "0")
    private Integer id;
    @Schema(description = "구매 코드", example = "SC0000")
    private String code;
    @Schema(description = "과목 이름", example = "수학")
    private String name;
    @Schema(description = "가격", example = "가격")
    private Integer price;
    @Schema(description = "유효 기간", example = "유효 기간")
    private Integer validDay;

    @Builder
    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.code =  subject.getCode();
        this.name = subject.getName();
        this.price = subject.getPrice();
        this.validDay = subject.getValidDay();
    }
}
