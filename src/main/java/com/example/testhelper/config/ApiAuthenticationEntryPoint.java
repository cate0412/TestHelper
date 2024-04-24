package com.example.testhelper.config;

import com.example.testhelper.exception.ApiException;
import com.example.testhelper.exception.enums.CommonExceptionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

//인증되지 않은 사용자가 접근할 때 예외 핸들링
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ApiException apiException = new ApiException(CommonExceptionEnum.AUTHORIZATION_FAILED);

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiException.getMessage()));
    }
}
