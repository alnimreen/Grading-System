package com.example.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GradeClient {
    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        int port = 5050;

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            Scanner scanner = new Scanner(System.in);


            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            writer.println("LOGIN");
            writer.println(firstName);
            writer.println(password);


            String response = reader.readLine();
            if ("LOGIN_SUCCESS".equals(response)) {
                System.out.println("Login successful!");

                String serverResponse;
                while ((serverResponse = reader.readLine()) != null) {
                    System.out.println(serverResponse);
                }
            } else {
                System.out.println("Login failed: " + response);  }

        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
