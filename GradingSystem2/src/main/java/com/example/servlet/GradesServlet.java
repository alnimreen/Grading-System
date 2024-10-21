package com.example.servlet;

import com.example.database.DatabaseConnection;
import com.example.model.Grade;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet("/grades")
public class GradesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer student_id = (Integer) session.getAttribute("student_id");
        if (student_id == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<Grade> grades = getStudentGrades(student_id);

        if (grades == null) {
            grades = new ArrayList<>();
        }

        request.setAttribute("grades", grades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("grades.jsp");
        dispatcher.forward(request, response);
    }

    private List<Grade> getStudentGrades(Integer studentId) {
        List<Grade> grades = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT c.course_name, g.grade FROM grades g " +
                    "JOIN students s ON g.student_id = s.student_id " +
                    "JOIN courses c ON g.course_id = c.course_id " +
                    "WHERE s.student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                double grade = rs.getDouble("grade");
                grades.add(new Grade(courseName, grade));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
}
