package com.example.testhelper.dto.subject;

import com.example.testhelper.domain.subject.Subject;
import com.example.testhelper.dto.test.TestResultDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectDetailDto {
    @Schema(description = "신청 Id", example = "0")
    private final Integer registerId;
    @Schema(description = "과목 Id", example = "0")
    private final Integer subjectId;
    @Schema(description = "과목 이름", example = "수학")
    private final String name;
    @Schema(description = "유효 기간", example = "유효 기간")
    private final Integer validDay;
    @Schema(description = "관련 테스트 목록", example = "관련 테스트 목록")
    private final List<TestResultDto> testResultList;

    @Builder
    public SubjectDetailDto(Integer registerId, Subject subject, List<TestResultDto> testResultList) {
        this.registerId = registerId;
        this.subjectId = subject.getId();
        this.name = subject.getName();
        this.validDay = subject.getValidDay();
        this.testResultList = testResultList;
    }
}
