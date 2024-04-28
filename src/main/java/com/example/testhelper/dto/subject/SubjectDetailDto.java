package com.example.testhelper.dto.subject;

import com.example.testhelper.domain.subject.Subject;
import com.example.testhelper.dto.test.TestResultDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectDetailDto {
    private Integer id;
    private String name;
    private Integer validDay;
    private List<TestResultDto> testResultList;

    @Builder
    public SubjectDetailDto(Subject subject, List<TestResultDto> testResultList) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.validDay = subject.getValidDay();
        this.testResultList = testResultList;
    }
}
