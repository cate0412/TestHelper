package com.example.testhelper.service.subjects;

import com.example.testhelper.domain.subject.Subject;

import com.example.testhelper.domain.subject.SubjectRepositoryCustom;
import com.example.testhelper.dto.subject.SubjectInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepositoryCustom subjectRepository;

    public List<SubjectInfoDto> getSubjectList(){
        List<Subject> subjects = subjectRepository.getSubjects();
        return subjects.stream().map(SubjectInfoDto::new).toList();
    }

    public SubjectInfoDto getSubject (Integer subjectId){
        Subject subject = subjectRepository.getSubject(subjectId);

        return new SubjectInfoDto(subject);
    }

}
