package com.example.testhelper.domain.user;

public interface UserRepositoryCustom {

    User getById(int id);

    User getByEmail(String email);

    User getTestUser();

    User findByEmailAndName(String email, String name);

}
