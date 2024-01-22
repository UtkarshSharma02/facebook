package com.facebook.service;

import com.facebook.entity.User;
import com.facebook.payload.UserDto;
import com.facebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Assuming you have a UserRepository

    @Override
    public UserDto createUser(UserDto createUserDTO) {
        User user = mapDtoToUser(createUserDTO);
        user.setUserId(UUID.randomUUID());
        User savedUser = userRepository.save(user);
        return mapUserToDTO(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(this::mapUserToDTO);
    }

    @Override
    public Optional<UserDto> updateUser(UUID userId, UserDto updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUserName(updateUserDTO.getUserName());
            existingUser.setUserImageURL(updateUserDTO.getUserImageURL());
            existingUser.setActivityStatus(updateUserDTO.getActivityStatus());
            User updatedUser = userRepository.save(existingUser);
            return Optional.of(mapUserToDTO(updatedUser));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteUser(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    private UserDto mapUserToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserImageURL(user.getUserImageURL());
        userDTO.setActivityStatus(user.getActivityStatus());
        userDTO.setJoinedDate(user.getJoinedDate());
        return userDTO;
    }

    private User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setUserImageURL(userDto.getUserImageURL());
        user.setActivityStatus(userDto.getActivityStatus());
        user.setJoinedDate(userDto.getJoinedDate());
        return user;
    }
}

