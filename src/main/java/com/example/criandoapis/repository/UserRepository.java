package com.example.criandoapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.criandoapis.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
