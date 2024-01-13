package com.example.backend.repository;

import java.util.Optional;

import com.example.backend.model.user.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(final String username);

}