package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Student;

import java.util.Scanner;

public class StudentUI {

    private Scanner scanner;
    private StudentAPI studentAPI;

    public StudentUI() {
        this.studentAPI = new StudentAPI();
        this.scanner = new Scanner(System.in);
    }

    public StudentUI(StudentAPI studentAPI) {
        this.studentAPI = studentAPI;
        this.scanner = new Scanner(System.in);
    }

    /**
     * UI function to get registration information
     */
    protected void register() {
        System.out.println("Thank you for taking time to register!");
        String username;
        String password;
        System.out.print("Please Enter a Username: ");
        username = scanner.nextLine();
        while (username.trim().equals("")) {
            System.out.println("Bad Username!");
            System.out.print("Please Enter a Username: ");
            username = scanner.nextLine();
        }
        System.out.print("Please Enter a Password: ");
        password = scanner.nextLine();
        while (password.trim().equals("")) {
            System.out.println("Bad Password!");
            System.out.print("Please Enter a Password: ");
            password = scanner.nextLine();
        }
        boolean registered = studentAPI.register(username, password);
        if (registered) {
            System.out.println("Thank you for registering as a new Student " + username);
        } else {
            System.out.println("An account with that username already exists. Sorry!");
        }
    }

    /**
     * UI function to let users login
     */
    protected Student login() {
        String username = "";
        String password = "";
        Student student = null;
        System.out.println("Login - Please Enter Your Credentials");
        System.out.print("Enter your Username: ");
        username = scanner.nextLine();
        while (username.trim().equals("")) {
            System.out.println("Bad Username!");
            System.out.print("Please Enter Your Username: ");
            username = scanner.nextLine();
        }
        System.out.print("Please Enter Your Password: ");
        password = scanner.nextLine();
        while (password.trim().equals("")) {
            System.out.println("Bad Password!");
            System.out.print("Please Enter Your Password: ");
            password = scanner.nextLine();
        }
        student = studentAPI.login(username, password);
        if (student != null) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Username or Password is Incorrect");
        }
        return student;
    }

    public void run() {
        Integer option = Integer.MAX_VALUE;
        Student student = null;
        String nullStudentOptions = " 0 - Quit\n" +
                " 1 - Login\n" +
                " 2 - Register\n";
        String loggedInOptions = " 0 - Quit\n" +
                " 1 - See Major Requirements\n" +
                " 2 - View Courses\n" +
                " 3 - Add Courses\n" +
                " 4 - Input Transcript\n";
        System.out.println("Welcome to Class Planner\n");
        while (option != 0) {
            if (student == null) {
                System.out.print("Please choose one\n" +
                        nullStudentOptions +
                        "Enter Selection Here: ");
                option = scanner.nextInt();
                while (option < 0 || option > 2) {
                    System.out.print("Invalid Selection\n" +
                            "Please choose one\n" +
                            nullStudentOptions +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                }
                if (option == 1) {
                    student = login();
                } else if (option == 2) {
                    register();
                }
            } else {
                System.out.print("Please choose one\n" +
                        loggedInOptions +
                        "Enter Selection Here: ");
                option = scanner.nextInt();
                while (option < 0 || option > 4) {
                    System.out.print("Invalid Selection\n" +
                            "Please Choose One\n" +
                            loggedInOptions +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                }
                if (option == 1) {
                    // TODO : Joe Major Requirements
                } else if (option == 2) {
                    // TODO : Dylan View Courses
                } else if (option == 3) {
                    // TODO : Dylan Add Course
                } else if (option == 4) {
                    // TODO : Dan Input Transcript
                }
            }
        }
        System.out.println("Thank you for using Class Planner");
    }
}
