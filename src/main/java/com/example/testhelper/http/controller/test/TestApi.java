package com.example.testhelper.http.controller.test;

import com.example.testhelper.domain.user.User;
import com.example.testhelper.dto.test.TestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Test", description = "테스트 관련")
public interface TestApi {


    @Operation(summary = "모의고사 조회 - 기능 구현중(작동X)")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TestDto.class))
    })
    TestDto getTest(@AuthenticationPrincipal User user, @PathVariable Integer testId);

}
