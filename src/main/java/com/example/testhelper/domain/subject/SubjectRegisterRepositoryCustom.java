package com.example.testhelper.domain.subject;

import com.example.testhelper.dto.subject.SubjectRegisterDto;
import com.example.testhelper.enums.SubjectRegisterEnum;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.testhelper.domain.subject.QSubjectRegister.subjectRegister;

@Repository
@RequiredArgsConstructor
public class SubjectRegisterRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Boolean checkSameRegister(Integer userId, Integer subjectId) {
        return queryFactory.from(subjectRegister)
                .select(subjectRegister.count())
                .where(subjectRegister.user.id.eq(userId),
                        subjectRegister.status.name.eq(SubjectRegisterEnum.ONGOING.code()),
                        subjectRegister.subject.id.eq(subjectId))
                .fetchFirst() > 0;
    }


    public List<SubjectRegisterDto> getRegisterList(Integer userId){
        return queryFactory.from(subjectRegister)
                .select(Projections.constructor(SubjectRegisterDto.class,
                        subjectRegister.id,
                        subjectRegister.status.name.as("subjectStatus"),
                        subjectRegister.status.label.as("subjectLabel"),
                        subjectRegister.subject.name.as("subjectName"),
                        subjectRegister.subject.price,
                        subjectRegister.subject.validDay))
                .where(subjectRegister.user.id.eq((userId)))
                .fetch();
    }

}
