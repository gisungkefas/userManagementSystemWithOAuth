package com.kefas.UserManagementSystemWithOAuth.service;

import com.kefas.UserManagementSystemWithOAuth.entities.User;
import com.kefas.UserManagementSystemWithOAuth.userDto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    String deleteUser(Long userId);

    User getUserById(Long userId);

    List<User> getAllUsers();
}
