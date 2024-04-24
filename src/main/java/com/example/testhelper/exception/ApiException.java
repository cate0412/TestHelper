package com.example.testhelper.exception;

import com.example.testhelper.exception.enums.ExceptionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private int status = HttpStatus.BAD_REQUEST.value();
    private String code = "ERROR";
    private String message = "An Error occurred.";
    private Map<String, Object> detail;

    public ApiException(ExceptionEnum exceptionEnum) {
        this.status = exceptionEnum.status();
        this.code = exceptionEnum.code();
        this.message = exceptionEnum.message();
    }

    public ApiException(ExceptionEnum exceptionEnum, Map<String, Object> detail) {
        this.status = exceptionEnum.status();
        this.code = exceptionEnum.code();
        this.message = exceptionEnum.message();
        this.detail = detail;
    }

}
