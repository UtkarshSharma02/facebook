package com.facebook.service;

import com.facebook.payload.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto createUserDTO);

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(UUID userId);

    Optional<UserDto> updateUser(UUID userId, UserDto updateUserDTO);

    boolean deleteUser(UUID userId);
}

