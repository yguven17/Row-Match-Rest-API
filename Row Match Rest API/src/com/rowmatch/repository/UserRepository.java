package com.rowmatch.repository;

import com.rowmatch.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    public User save(User user) {
        return user;
    }

    public Optional<Object> findById(Long userId) {
        return null;
    }

    public static List<User> findAll() {
        return User.all();
    }

    public void deleteById(Long userId) {
    }
}
