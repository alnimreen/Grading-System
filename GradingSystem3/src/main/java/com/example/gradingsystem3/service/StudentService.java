package com.example.gradingsystem3.service;


import com.example.gradingsystem3.model.Student;
import com.example.gradingsystem3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> authenticate(String firstName) {
        Optional<Student> student = studentRepository.findByFirstName(firstName);
        return student;
    }
}