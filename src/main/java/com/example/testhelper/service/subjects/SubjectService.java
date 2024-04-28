package com.example.testhelper.service.subjects;

import com.example.testhelper.domain.subject.*;

import com.example.testhelper.domain.system.CodeRepository;
import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.subject.SubjectDetailDto;
import com.example.testhelper.dto.subject.SubjectDto;
import com.example.testhelper.dto.subject.SubjectRegisterDto;
import com.example.testhelper.enums.SubjectRegisterEnum;
import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import com.example.testhelper.exception.enums.SubjectExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepositoryCustom subjectRepository;
    private final SubjectRegisterRepository registerRepository;
    private final SubjectRegisterRepositoryCustom registerRepositoryCustom;
    private final CodeRepository codeRepository;

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

    public SubjectDetailDto getSubjectDetail(User user, Integer subjectId){
        return null;
    }

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

        SubjectRegister subjectRegister = registerRepository.save(SubjectRegister.builder()
                .user(user)
                .subject(this.subject)
                .status(codeRepository.getReferenceById(SubjectRegisterEnum.ONGOING.code()))
                .build());

        return subjectRegister.getId();
    }

}
