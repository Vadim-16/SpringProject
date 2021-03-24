package ca.purpose.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentInitializer {

    public Student initStudent() {
        Student student = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Please enter your first name:");
            String firstName = reader.readLine();
            System.out.print("Please enter your last name:");
            String lastName = reader.readLine();
            student = new Student(firstName, lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }
}
