package com.example.socket;

import com.example.cli.StudentGrades;
import com.example.cli.StudentLogin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GradeServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            System.out.println("Server is listening on port 5050");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);


                String command = reader.readLine();
                if ("LOGIN".equals(command)) {
                    String firstName = reader.readLine();
                    String password = reader.readLine();

                    boolean isAuthenticated = StudentLogin.login(firstName, password);
                    if (isAuthenticated) {
                        writer.println("LOGIN_SUCCESS");
                        String studentId = StudentLogin.getStudentId(firstName);
                        String grades = StudentGrades.getGradesForSocket(Integer.parseInt(studentId));
                        writer.println(grades);
                    } else {
                        writer.println("LOGIN_FAILED");
                        socket.close();
                        continue;
                    }
                }
                socket.close();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
