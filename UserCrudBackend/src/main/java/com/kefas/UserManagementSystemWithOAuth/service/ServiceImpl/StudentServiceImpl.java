package com.kefas.UserManagementSystemWithOAuth.service.ServiceImpl;

import com.kefas.UserManagementSystemWithOAuth.entities.Student;
import com.kefas.UserManagementSystemWithOAuth.exception.StudentAlreadyExistException;
import com.kefas.UserManagementSystemWithOAuth.repository.StudentRepository;
import com.kefas.UserManagementSystemWithOAuth.service.StudentService;
import com.kefas.UserManagementSystemWithOAuth.studentDto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student students = new Student();

        boolean existUser = studentRepository.findByEmail(studentDto.getEmail()).isPresent();
        if(existUser){
            throw new StudentAlreadyExistException("User with "+studentDto.getEmail()+"Already Exist");
        }

        students.setEmail(studentDto.getEmail());
        students.setFirstName(studentDto.getFirstName());
        students.setLastName(studentDto.getLastName());
        students.setGender(studentDto.getGender());
        students.setDob(studentDto.getDob());
        students.setPhoneNumber(studentDto.getPhoneNumber());
        students.setAddress(studentDto.getAddress());
        studentRepository.save(students);

        return studentDto;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
