package com.example.gradingsystem3.controller;


import com.example.gradingsystem3.model.Student;
import com.example.gradingsystem3.model.Grade;
import com.example.gradingsystem3.service.StudentService;
import com.example.gradingsystem3.service.GradeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @PostMapping("/login")
    public String login(@RequestParam String firstName,
                        @RequestParam String password,
                        Model model, HttpSession session) {

        Optional<Student>student = studentService.authenticate(firstName);

        if (firstName != null && !firstName.isEmpty() && student.isPresent()) {
            Student user = student.get();

            if (user.getPassword().equals(password)) {
                Integer studentId = user.getStudentId();
                session.setAttribute("userId", studentId);

                return "redirect:/grades";
            } else {
                model.addAttribute("error", "Invalid password.");
                return "login";
            }
        } else {
            model.addAttribute("error", "User not found.");
            return "login";
        }
    }


    @GetMapping("/grades")
    public String getGrades(HttpSession session, Model model) {
        Integer studentId = (Integer) session.getAttribute("userId");
        if (studentId == null) {
            return "redirect:/";
        }
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);

        model.addAttribute("grades", grades);
        return "Grades";
    }
}