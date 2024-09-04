package com.example.criandoapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.criandoapis.entities.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
