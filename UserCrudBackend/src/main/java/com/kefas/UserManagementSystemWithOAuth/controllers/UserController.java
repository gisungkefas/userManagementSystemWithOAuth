package com.kefas.UserManagementSystemWithOAuth.controllers;

import com.kefas.UserManagementSystemWithOAuth.entities.User;
import com.kefas.UserManagementSystemWithOAuth.service.UserService;
import com.kefas.UserManagementSystemWithOAuth.userDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{userId}/edit-user")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto, @PathVariable Long userId) {
        UserDto editUser = userService.editUser(userDto, userId);
        return new ResponseEntity<>(editUser, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}/delete-user")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
