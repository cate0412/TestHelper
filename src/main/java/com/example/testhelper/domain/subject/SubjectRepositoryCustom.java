package com.example.testhelper.domain.subject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.testhelper.domain.subject.QSubject.subject;


@Repository
@RequiredArgsConstructor
public class SubjectRepositoryCustom  {

    private final JPAQueryFactory queryFactory;


    public List<Subject>getSubjects(){
        return queryFactory.selectFrom(subject).fetch();
    }

    public Subject getSubject(Integer subjectId){
        return queryFactory.selectFrom(subject)
                .where(subject.id.eq(subjectId))
                .fetchOne();
    }


}
