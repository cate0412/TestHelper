package com.example.testhelper.service.subjects;

import com.example.testhelper.domain.subject.*;

import com.example.testhelper.domain.system.CodeRepository;
import com.example.testhelper.domain.test.TestResultRepositoryCustom;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.subject.SubjectDetailDto;
import com.example.testhelper.dto.subject.SubjectDto;
import com.example.testhelper.dto.subject.SubjectRegisterDto;
import com.example.testhelper.dto.test.TestResultDto;
import com.example.testhelper.enums.SubjectRegisterEnum;
import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import com.example.testhelper.exception.enums.SubjectExceptionEnum;
import com.example.testhelper.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepositoryCustom subjectRepository;
    private final SubjectRegisterRepository registerRepository;
    private final SubjectRegisterRepositoryCustom registerRepositoryCustom;
    private final CodeRepository codeRepository;
    private final TestService testService;
    private final TestResultRepositoryCustom testResultRepositoryCustom;

    private Subject subject;

    public List<SubjectDto> getSubjectList() {
        List<Subject> subjects = subjectRepository.getSubjectList();

        return subjects.stream().map(SubjectDto::new).toList();
    }

    public SubjectDto getSubject(Integer subjectId) {
        this.subject = subjectRepository.getSubject(subjectId);
        return new SubjectDto(subject);
    }

    public List<SubjectRegisterDto> getRegisterList(User user) {
        return registerRepositoryCustom.getRegisterList(user.getId());
    }

    public SubjectDetailDto getSubjectDetail(User user, Integer subjectId) {
        SubjectRegister subjectRegister = registerRepositoryCustom.getSubjectRegister(user.getId(), subjectId);

        if (subjectRegister == null) {
            throw new ApiException(CommonExceptionEnum.NOT_FOUND);
        }

        List<TestResultDto> testResultList = testResultRepositoryCustom.getTestResult(user.getId(), subjectId);

        return SubjectDetailDto.builder()
                .registerId(subjectRegister.getId())
                .subject(subjectRegister.getSubject())
                .testResultList(testResultList)
                .build();
    }

    @Transactional
    public Integer registerSubject(User user, Integer subjectId) {

        //과목유무 확인
        this.subject = subjectRepository.getSubject(subjectId);
        if (this.subject == null) {
            throw new ApiException(CommonExceptionEnum.NOT_FOUND);
        }

        //과목 진행여부 확인
        if (registerRepositoryCustom.checkSameRegister(user.getId(), subjectId)) {
            throw new ApiException(SubjectExceptionEnum.ONGOING_SUBJECT);
        }

        //신청 정보 생성
        SubjectRegister subjectRegister = registerRepository.save(SubjectRegister.builder()
                .user(user)
                .subject(this.subject)
                .status(codeRepository.getReferenceById(SubjectRegisterEnum.ONGOING.code()))
                .build());

        //테스트결과 초기 데이터 생성
        testService.setTestResult(subjectRegister);

        return subjectRegister.getId();
    }

    @Transactional
    public void deleteSubject(User user, Integer subjectId) {
        SubjectRegister targetSubject = registerRepositoryCustom.getSubjectRegister(user.getId(), subjectId);

        if (targetSubject == null) {
            throw new ApiException(CommonExceptionEnum.NOT_FOUND);
        }

        //신청과목 관련 테스트 결과기록 삭제
        testService.deleteTestResult(user.getId(), subjectId);

        //신청 과목 데이터 삭제
        registerRepository.delete(targetSubject);

    }

}
