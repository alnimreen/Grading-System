package com.example.model;

public class Grade {
    private String courseName;
    private double grade;

    public Grade(String courseName, double grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getGrade() {
        return grade;
    }
}