package com.example.testhelper.dto.subject;

import com.example.testhelper.domain.subject.SubjectRegister;
import lombok.Getter;

@Getter
public class SubjectRegisterListDto {

    private Integer id;
    private String name;
    private Integer price;
    private Integer validDay;

    public SubjectRegisterListDto(SubjectRegister subjectRegister){
        this.id = subjectRegister.getId();
        this.name = subjectRegister.getSubject().getName();
        this.price = subjectRegister.getSubject().getPrice();
        this.validDay = subjectRegister.getSubject().getValidDay();
    }

}
