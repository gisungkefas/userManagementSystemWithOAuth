package com.kefas.UserManagementSystemWithOAuth.service;

import com.kefas.UserManagementSystemWithOAuth.entities.Users;
import com.kefas.UserManagementSystemWithOAuth.userDto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto editUser(UserDto userDto, Long userId);

    String deleteUser(Long userId);

    Users getUserById(Long userId);

    List<Users> getAllUsers();
}
