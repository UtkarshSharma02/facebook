package com.facebook.controller;

import com.facebook.payload.UserDto;
import com.facebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user

    //http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto createUserDTO) {
        UserDto savedUser = userService.createUser(createUserDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        Optional<UserDto> optionalUser = userService.getUserById(userId);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update user by ID
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID userId, @RequestBody UserDto updateUserDTO) {
        Optional<UserDto> optionalUser = userService.updateUser(userId, updateUserDTO);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        boolean deleted = userService.deleteUser(userId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}