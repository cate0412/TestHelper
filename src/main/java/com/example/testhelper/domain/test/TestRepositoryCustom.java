package com.example.testhelper.domain.test;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.testhelper.domain.test.QTest.test;

@Repository
@RequiredArgsConstructor
public class TestRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<Test> getTestList(Integer subjectId){
        return queryFactory.selectFrom(test)
                .where(test.subject.id.eq(subjectId))
                .fetch();
    }
}
