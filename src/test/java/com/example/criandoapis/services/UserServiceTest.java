package com.example.criandoapis.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import com.example.criandoapis.entities.User;
import com.example.criandoapis.exceptions.UserNotFoundException;
import com.example.criandoapis.exceptions.UsernameAlreadyExistsException;
import com.example.criandoapis.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp(){
        userId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        user.setUsername("John");
        user.setPassword("teste");
    }

    @Test
    void testSaveUser_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        verify(userRepository, times(1)).existsByUsername(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveUser_UsernameAlreadyExists() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        UsernameAlreadyExistsException thrown = assertThrows(
            UsernameAlreadyExistsException.class,
            () -> userService.saveUser(user)
        );

        assertEquals("Username already exists: John", thrown.getMessage());
        verify(userRepository, times(1)).existsByUsername(anyString());
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.getUserById(userId);

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        verify(userRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());

        UserNotFoundException thrown = assertThrows(
            UserNotFoundException.class,
            () -> userService.getUserById(userId)
        );

        assertEquals("User not found with id: " + userId, thrown.getMessage());
        verify(userRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(any(UUID.class))).thenReturn(true);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).existsById(any(UUID.class));
        verify(userRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void testDeleteUser_UserNotFound() {
        when(userRepository.existsById(any(UUID.class))).thenReturn(false);

        UserNotFoundException thrown = assertThrows(
            UserNotFoundException.class,
            () -> userService.deleteUser(userId)
        );

        assertEquals("User not found with id: " + userId, thrown.getMessage());
        verify(userRepository, times(1)).existsById(any(UUID.class));
        verify(userRepository, times(0)).deleteById(any(UUID.class));
    }

    @Test
    void testEditUser_Success() {
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setUsername("updateduser");
        updatedUser.setPassword("newpassword");

        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.editUser(updatedUser);

        assertNotNull(result);
        assertEquals("updateduser", result.getUsername());
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testEditUser_UserNotFound() {
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setUsername("updateduser");
        updatedUser.setPassword("newpassword");

        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());

        UserNotFoundException thrown = assertThrows(
            UserNotFoundException.class,
            () -> userService.editUser(updatedUser)
        );

        assertEquals("User not found with id: " + userId, thrown.getMessage());
        verify(userRepository, times(1)).findById(any(UUID.class));
        verify(userRepository, times(0)).save(any(User.class));
    }

}
