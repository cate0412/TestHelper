package com.example.testhelper.http.controller.subject;

import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.subject.SubjectDetailDto;
import com.example.testhelper.dto.subject.SubjectDto;
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
    public List<SubjectDto> getSubjectList() {
        return subjectService.getSubjectList();
    }

    @GetMapping("/{subjectId}")
    @ResponseStatus(HttpStatus.OK)
    public SubjectDto getSubject(@PathVariable Integer subjectId) {
        return subjectService.getSubject(subjectId);
    }


    @GetMapping("/registerList")
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectRegisterDto> getRegisterList(@AuthenticationPrincipal User user) {
        return subjectService.getRegisterList(user);
    }

    @GetMapping("/registerList/{subjectId}")
    public SubjectDetailDto getSubjectDetail(@AuthenticationPrincipal User user,
                                             @PathVariable Integer subjectId) {
        return subjectService.getSubjectDetail(user, subjectId);
    }

    @PostMapping("/{subjectId}/register")
    public Integer registerSubject(@AuthenticationPrincipal User user,
                                   @PathVariable Integer subjectId) {
        return subjectService.registerSubject(user, subjectId);
    }

}
