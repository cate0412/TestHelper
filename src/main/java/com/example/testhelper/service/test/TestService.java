package com.example.testhelper.service.test;

import com.example.testhelper.domain.subject.SubjectRegister;
import com.example.testhelper.domain.system.Code;
import com.example.testhelper.domain.system.CodeRepository;
import com.example.testhelper.domain.test.*;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.test.TestDto;
import com.example.testhelper.enums.TestResultStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final CodeRepository codeRepository;
    private final TestResultRepositoryCustom testResultRepositoryCustom;
    private final TestResultRepository testResultRepository;
    private final TestRepositoryCustom testRepositoryCustom;


    public TestDto getTest(User user, Integer testResultId){
        return null;
    }


    public void setTestResult(SubjectRegister subjectRegister){
        User user = subjectRegister.getUser();
        List<Test> testList = testRepositoryCustom.getTestList(subjectRegister.getSubject().getId());
        Code testResultStatus = codeRepository.getReferenceById(TestResultStatusEnum.NOT_START.code());

        testList.forEach(test -> {
            TestResult testResult = TestResult.builder()
                    .test(test)
                    .user(user)
                    .status(testResultStatus)
                    .build();
           testResultRepository.save(testResult);
        });
    }

    public void deleteTestResult(Integer userId, Integer subjectId){

        List<TestResult> testResultList = testResultRepositoryCustom.getTestResultList(userId, subjectId);
        testResultRepository.deleteAll(testResultList);
    }
}
