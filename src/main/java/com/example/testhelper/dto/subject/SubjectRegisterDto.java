package com.example.testhelper.dto.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectRegisterDto {

    @Schema(description = "신청 과목 Id", example = "신청 과목 Id")
    private Integer registerId;
    @Schema(description = "과목 진행 상태", example = "과목 진행 상태")
    private String subjectStatus;
    @Schema(description = "과목 진행 상태 라벨", example = "진행중")
    private String subjectLabel;
    @Schema(description = "과목 이름", example = "과목 이름")
    private String subjectName;
    @Schema(description = "과목 가격", example = "과목 가격")
    private Integer price;
    @Schema(description = "유효 기간", example = "유효 기간")
    private Integer validDay;

}
