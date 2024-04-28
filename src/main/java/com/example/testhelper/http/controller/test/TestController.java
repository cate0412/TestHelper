package com.example.testhelper.http.controller.test;

import com.example.testhelper.domain.user.User;

import com.example.testhelper.dto.test.TestDto;
import com.example.testhelper.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController implements TestApi {

    private final TestService testService;

    @GetMapping("/{testResultId}")
    public TestDto getTest(@AuthenticationPrincipal User user,
                           @PathVariable Integer testResultId) {

        return testService.getTest(user, testResultId);
    }


/*
    @PostMapping("/{testId}")
    public void createTestResult(@AuthenticationPrincipal User user,
                                   @PathVariable Integer testId,
                                   @RequestBody TestResultRequestDto requestDto) {
        testService.setTestResult(user.getId(), testId, requestDto.getSurvey());
    }

    @GetMapping("/{testResultId}/result")
    public TestResultResponseDto.TestyResultDto getTestResult(@AuthenticationPrincipal User user,
                                                   @PathVariable Integer testResultId) {
        return testService.getTestResult(user , testResultId);
    }
     */
}
