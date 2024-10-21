package com.example.cli;

import com.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentLogin {


    public static boolean login(String firstName, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT password FROM students WHERE first_name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword);
            } else {
                System.out.println("Invalid first name.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String getStudentId(String firstName) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT student_id FROM students WHERE first_name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("student_id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
