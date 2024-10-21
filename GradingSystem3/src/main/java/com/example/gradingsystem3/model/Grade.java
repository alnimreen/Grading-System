package com.example.gradingsystem3.model;


import jakarta.persistence.*;

@Entity
@Table(name = "grades")

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Integer gradeId;
    @Column(name = "student_id")
    private Integer studentId;
    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    private Integer grade;



    public Integer getGradeId() {
        return gradeId;
    }

    public Integer getStudent() {
        return studentId;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getGrade() {
        return grade;
    }
}
