package com.example.testhelper.domain.test;

import com.example.testhelper.dto.test.TestResultDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.testhelper.domain.test.QTestResult.testResult;

@Repository
@RequiredArgsConstructor
public class TestResultRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<TestResultDto> getTestResult(Integer userId, Integer subjectId) {
        return queryFactory.from(testResult)
                .select(Projections.constructor(TestResultDto.class,
                        testResult.id,
                        testResult.test.name.as("testName"),
                        testResult.status.name,
                        testResult.status.label))
                .where(testResult.user.id.eq(userId),
                        testResult.test.subject.id.eq(subjectId))
                .fetch();
    }

    public List<TestResult> getTestResultList(Integer userId, Integer subjectId) {
        return queryFactory.selectFrom(testResult)
                .where(testResult.user.id.eq(userId),
                        testResult.test.subject.id.eq(subjectId))
                .fetch();
    }

}
