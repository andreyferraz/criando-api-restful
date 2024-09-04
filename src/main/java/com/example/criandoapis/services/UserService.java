package com.example.criandoapis.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.criandoapis.entities.User;
import com.example.criandoapis.exceptions.UserNotFoundException;
import com.example.criandoapis.exceptions.UsernameAlreadyExistsException;
import com.example.criandoapis.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
            if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        if(userRepository.existsById(id) == false){
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User editUser(User user){
        UUID id = user.getId();
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;            
    }
}
