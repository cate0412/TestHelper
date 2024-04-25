package com.example.testhelper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public <T> String toJson(T data) throws JsonProcessingException {

        return objectMapper.writeValueAsString(data);
    }
}
