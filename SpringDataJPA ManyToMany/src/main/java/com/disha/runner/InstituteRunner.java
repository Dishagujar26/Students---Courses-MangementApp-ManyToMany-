package com.disha.runner;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.disha.service.InstituteService;

import java.util.Scanner;

@Component
public class InstituteRunner implements CommandLineRunner {

    @Autowired
    private InstituteService serv;

    private Scanner kb = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("\n================ Institute Management System ================");
            System.out.println("1. Create Course");
            System.out.println("2. Create Student");
            System.out.println("3. Enroll Student In Course");
            System.out.println("4. List Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Delete Course");
            System.out.println("7. List Courses");
            System.out.println("8. Exit");
            System.out.print("\nChoose an option: ");

            int choice = readIntegerInput();

            switch (choice) {
                case 1:
                    createCourse();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    enrollStudentInCourse();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    deleteCourse();
                    break;
                case 7:
                    listCourses();
                    break;
                case 8:
                	System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createCourse() {
        System.out.print("Enter course name: ");
        String courseName = kb.nextLine();
        serv.addCourse(courseName);
        System.out.println("Course added successfully!");
    }

    private void createStudent() {
        System.out.print("Enter student name: ");
        String studentName = kb.nextLine();
        serv.addStudent(studentName);
        System.out.println("Student added successfully!");
    }

    private void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        Integer studentId = readIntegerInput();

        System.out.print("Enter course ID: ");
        Integer courseId = readIntegerInput();

        serv.addStudentToCourse(studentId, courseId);
        System.out.println("Student enrolled in course successfully!");
    }

    private void listStudents() {
        System.out.println("\nList of Students:");
        serv.listOfStudent(); // Ensure this method prints details or returns a collection to display.
    }
    private void listCourses() {
        System.out.println("\nList of Courses:");
        serv.listOfCourses(); // Ensure this method prints details or returns a collection to display.
    }

    private void deleteStudent() {
        System.out.print("Enter student ID to be deleted: ");
        Integer studentId = readIntegerInput();
        serv.deleteStudent(studentId);
        System.out.println("Student deleted successfully!");
    }

    private void deleteCourse() {
        System.out.print("Enter course ID to be deleted: ");
        Integer courseId = readIntegerInput();
        serv.deleteCourse(courseId);
        System.out.println("Course deleted successfully!");
    }

    private int readIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(kb.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}

