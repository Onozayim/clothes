package com.clothes.clothes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clothes.clothes.entities.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE email= ?1 limit 1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
