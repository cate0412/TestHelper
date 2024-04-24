package com.example.testhelper.dto.subject;

import com.example.testhelper.domain.subject.Subject;
import lombok.Getter;

@Getter
public class SubjectInfoDto {

    private Integer id;
    private String code;
    private String name;
    private Integer price;
    private Integer validDay;

    public SubjectInfoDto(Subject subject) {
        this.id = subject.getId();
        this.code =  subject.getCode();
        this.name = subject.getName();
        this.price = subject.getPrice();
        this.validDay = subject.getValidDay();
    }

}
