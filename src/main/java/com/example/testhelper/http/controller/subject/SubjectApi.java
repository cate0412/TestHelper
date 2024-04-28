package com.example.testhelper.http.controller.subject;


import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.subject.SubjectDetailDto;
import com.example.testhelper.dto.subject.SubjectDto;
import com.example.testhelper.dto.subject.SubjectRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Subject", description = "과목관련")
public interface SubjectApi {

    @Operation(summary = "과목 목록 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDto.class))
    })
    List<SubjectDto> getSubjectList();

    @Operation(summary = "과목 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDto.class))
    })
    SubjectDto getSubject(@PathVariable Integer subjectId);

    @Operation(summary = "신청 과목 목록 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectRegisterDto.class))
    })
    List<SubjectRegisterDto> getRegisterList(@AuthenticationPrincipal User user);


    @Operation(summary = "신청 과목 상세 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectDetailDto.class))
    })
    SubjectDetailDto getSubjectDetail(@AuthenticationPrincipal User user,
                                      @PathVariable Integer subjectId);


    @Operation(summary = "과목 신청")
    Integer registerSubject(@AuthenticationPrincipal User user, @PathVariable Integer subjectId);
}
