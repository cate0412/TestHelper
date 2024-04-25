package com.example.testhelper.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubjectRegisterDto {

    private Integer id;
    private String subjectStatus;
    private String subjectLabel;
    private String subjectName;
    private Integer price;
    private Integer validDay;

}
