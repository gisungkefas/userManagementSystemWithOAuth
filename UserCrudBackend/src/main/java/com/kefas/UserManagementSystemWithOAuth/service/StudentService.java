package com.kefas.UserManagementSystemWithOAuth.service;

import com.kefas.UserManagementSystemWithOAuth.entities.Student;
import com.kefas.UserManagementSystemWithOAuth.studentDto.StudentDto;
import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto studentDto);

    List<Student> getAllStudent();
}
