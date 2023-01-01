package com.kefas.UserManagementSystemWithOAuth.service.ServiceImpl;

import com.kefas.UserManagementSystemWithOAuth.entities.User;
import com.kefas.UserManagementSystemWithOAuth.exception.UserAlreadyExistException;
import com.kefas.UserManagementSystemWithOAuth.repository.UserRepository;
import com.kefas.UserManagementSystemWithOAuth.service.UserService;
import com.kefas.UserManagementSystemWithOAuth.userDto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//        students.setEmail(userDto.getEmail());
//        students.setFirstName(userDto.getFirstName());
//        students.setLastName(userDto.getLastName());
//        students.setGender(userDto.getGender());
//        students.setDob(userDto.getDob());
//        students.setPhoneNumber(userDto.getPhoneNumber());
//        students.setAddress(userDto.getAddress());
        BeanUtils.copyProperties(userDto, users);
        userRepository.save(users);

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
