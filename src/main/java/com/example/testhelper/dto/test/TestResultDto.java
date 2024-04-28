package com.example.testhelper.dto.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestResultDto {
    private Integer id;
    private String testName;
    private String status;
    private String statusLabel;
}
