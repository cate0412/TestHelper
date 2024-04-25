package com.example.testhelper.http.controller.subject;

import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.subject.SubjectInfoDto;
import com.example.testhelper.dto.subject.SubjectRegisterDto;
import com.example.testhelper.service.subjects.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject")
public class SubjectController implements SubjectApi {

    private final SubjectService subjectService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectInfoDto> getSubjectList() {
        return subjectService.getSubjectList();
    }

    @GetMapping("/registerList")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectRegisterDto> getRegisterList(@AuthenticationPrincipal User user) {
        return subjectService.getRegisterList(user);
    }

    @GetMapping("/{subjectId}")
    public SubjectInfoDto getSubjectInfo(@PathVariable Integer subjectId) {
        return subjectService.getSubject(subjectId);
    }

    @PostMapping("/{subjectId}/register")
    public Integer registerSubject(@AuthenticationPrincipal User user,
                                   @PathVariable Integer subjectId) {
        return subjectService.registerSubject(user, subjectId);
    }

}
