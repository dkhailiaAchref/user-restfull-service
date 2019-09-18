package com.achrefdkhailia.code.service;

import com.achrefdkhailia.code.domain.User;

import java.util.List;

public interface UserService {

    User save(final User user);
    User findById(final Long id);
    List<User> findAll();
    User update(final long id, final User user);
    void deleteById(final long id);
}
