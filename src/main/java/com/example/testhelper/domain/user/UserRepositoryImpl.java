package com.example.testhelper.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.testhelper.domain.user.QUser.user;


@NoArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    JPAQueryFactory queryFactory;

    @Override
    public User getById(int id) {
        return queryFactory.selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    @Override
    public User getByEmail(String email) {
        return queryFactory.selectFrom(user)
                .where(user.email.eq(email))
                .fetchOne();
    }

    @Override
    public User getTestUser() {
        return queryFactory.selectFrom(user)
                .where(user.id.eq(1))
                .fetchOne();
    }

    @Override
    public User findByEmailAndName(String email, String name) {
        return queryFactory.selectFrom(user)
                .where(user.email.eq(email).and(user.name.eq(name)))
                .fetchOne();
    }
}
