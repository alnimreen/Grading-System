package com.example.gradingsystem3.service;

import com.example.gradingsystem3.model.Grade;
import com.example.gradingsystem3.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGradesByStudentId(Integer studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}