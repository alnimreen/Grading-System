package com.example.cli;

import com.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentGrades {

    public static String getGradesForSocket(int studentId) {
        StringBuilder result = new StringBuilder();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT c.course_name, g.grade FROM Grades g " +
                    "JOIN Courses c ON g.course_id = c.course_id " +
                    "WHERE g.student_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String grade = rs.getString("grade");
                result.append(courseName).append(": ").append(grade).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
