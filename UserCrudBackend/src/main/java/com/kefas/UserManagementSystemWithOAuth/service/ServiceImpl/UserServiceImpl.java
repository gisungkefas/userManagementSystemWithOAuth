package com.kefas.UserManagementSystemWithOAuth.service.ServiceImpl;

import com.kefas.UserManagementSystemWithOAuth.entities.User;
import com.kefas.UserManagementSystemWithOAuth.exception.UserAlreadyExistException;
import com.kefas.UserManagementSystemWithOAuth.exception.UserNotFoundException;
import com.kefas.UserManagementSystemWithOAuth.repository.UserRepository;
import com.kefas.UserManagementSystemWithOAuth.service.UserService;
import com.kefas.UserManagementSystemWithOAuth.userDto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {
        User users = new User();

        boolean existUser = userRepository.findByEmail(userDto.getEmail()).isPresent();
        if(existUser){
            throw new UserAlreadyExistException("User with "+userDto.getEmail()+"Already Exist");
        }

        users.setEmail(userDto.getEmail());
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setGender(userDto.getGender());
        users.setDob(userDto.getDob());
        users.setPhoneNumber(userDto.getPhoneNumber());
        users.setAddress(userDto.getAddress());
//        BeanUtils.copyProperties(userDto, users);
        userRepository.save(users);

        return userDto;
    }

    @Override
    public UserDto editUser(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException("Post with ID: "+ userId +" is not found"));

        if(userDto.getEmail() != null && !userDto.getEmail().equals(user.getEmail())){
            user.setEmail(userDto.getEmail());
        }

        if(userDto.getFirstName() != null && !userDto.getFirstName().equals(user.getFirstName())){
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null && !userDto.getLastName().equals(user.getLastName())){
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getGender() != null && !userDto.getGender().equals(user.getGender())){
            user.setGender(userDto.getGender());
        }

        if (userDto.getDob() != null && !userDto.getDob().equals(user.getDob())){
            user.setDob(userDto.getDob());
        }

        if (userDto.getPhoneNumber() != null && !userDto.getPhoneNumber().equals(user.getPhoneNumber())){
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        if (userDto.getAddress() != null && !userDto.getAddress().equals(user.getAddress())){
            user.setAddress(userDto.getAddress());
        }

        userRepository.save(user);

        return userDto;
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException("User with ID: "+ userId +" is not found"));

        userRepository.delete(user);
        return "Post Deleted Successfully";
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException("Post with ID: " + userId + " Not Found");
                });
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
