package com.example.gradingsystem3.model;


import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "course_name")
    private String courseName;

    // Getters and Setters

    public String getCourseName() {
        return courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }
}