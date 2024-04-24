package com.example.testhelper.http.controller.subject;


import com.example.testhelper.dto.subject.SubjectInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Subject", description = "과목관련")
public interface SubjectApi {

    @Operation(summary = "과목 목록 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectInfoDto.class))
    })
    List<SubjectInfoDto> getSubjectList();

    @Operation(summary = "과목 상세 조회")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectInfoDto.class))
    })
    SubjectInfoDto getSubjectInfo(Integer subjectId);

}
