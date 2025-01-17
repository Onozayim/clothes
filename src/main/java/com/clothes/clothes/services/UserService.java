package com.clothes.clothes.services;

import java.util.Optional;

import com.clothes.clothes.entities.User;

public interface UserService {
    Optional<User> findByEmail(String email);
    void deleteUserById(Long id);
    Optional<User> findUserById(Long id);
    User findUserByIdOrThrow(Long id);
}
