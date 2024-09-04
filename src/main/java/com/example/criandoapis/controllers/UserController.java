package com.example.criandoapis.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.criandoapis.entities.User;
import com.example.criandoapis.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user){
        if(!id.equals(user.getId())){
            throw new IllegalArgumentException("ID do usuário não corresponde ao ID fornecido");
        }
        User editedUser = userService.editUser(user);
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
    
}
