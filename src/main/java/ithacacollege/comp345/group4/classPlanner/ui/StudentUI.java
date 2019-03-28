package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Major;
import ithacacollege.comp345.group4.classPlanner.model.Student;
import ithacacollege.comp345.group4.classPlanner.model.Transcript;

import java.util.ArrayList;
import java.util.List;
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
        username = scanner.next();
        while (username.trim().equals("")) {
            System.out.println("Bad Username!");
            System.out.print("Please Enter a Username: ");
            username = scanner.next();
        }
        System.out.print("Please Enter a Password: ");
        password = scanner.next();
        while (password.trim().equals("")) {
            System.out.println("Bad Password!");
            System.out.print("Please Enter a Password: ");
            password = scanner.next();
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
        username = scanner.next();
        while (username.trim().equals("")) {
            System.out.println("Bad Username!");
            System.out.print("Please Enter Your Username: ");
            username = scanner.next();
        }
        System.out.print("Please Enter Your Password: ");
        password = scanner.next();
        while (password.trim().equals("")) {
            System.out.println("Bad Password!");
            System.out.print("Please Enter Your Password: ");
            password = scanner.next();
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
                    //Currently student object has no major, so just using Computer Science for now

                    //String majorString = student.getMajor().title;
                    String majorString = "Computer Science";
                    List<Major.Requirement> reqs = studentAPI.viewMajorRequirment(majorString);
                    for(Major.Requirement req : reqs){
                        System.out.println(req.toString());
                    }
                } else if (option == 2) {
                    // TODO : Dylan View Courses
                    /*System.out.print("Please choose one:\n" +
                            "1. View Past Courses\n" +
                            "2. View Current Courses\n" +
                            "3. View Planned Courses\n"
                            "Enter Selection Here: ");*/
                    int courseOp = 2; //scanner.nextInt();
                    /*
                    while (courseOp < 0 || courseOp > 4) {
                        System.out.print("Invalid Selection\n" +
                                "Please choose one:\n" +
                                "1. View Past Courses\n" +
                                "2. View Current Courses\n" +
                                "3. View Planned Courses\n
                                "Enter Selection Here: "");
                    }*/
                    
                    switch (courseOp){
                        /*case 1:
                            studentAPI.viewTakenCourses(student.getUsername());
                            break;*/
                        case 2:
                            System.out.print("\nCurrent courses " + student.getUsername()
                                    + " is enrolled in:\n" + studentAPI.viewCurrentCourses(student.getUsername())+"\n\n");
                            break;
                        /*case 3:
                            studentAPI.viewPlannedCourses(student.getUsername());
                            break;*/

                    }
                } else if (option == 3) {
                    // TODO : Dylan Add Course
                    System.out.print("Please choose one:\n" +
                            "1. Add Past Courses\n" +
                            "2. Add Current Courses\n" +
                            "3. Add Planned Courses\n" +
                            "Enter Selection Here: ");
                    int addOp = scanner.nextInt();
                    System.out.print("Enter the information for the course\n" +
                            "Name: ");
                    String name = scanner.next();
                    System.out.print("CRN: ");
                    int CRN = scanner.nextInt();
                    System.out.print("Credits: ");
                    double credits = scanner.nextDouble();
                    System.out.print("Department & Number: ");
                    String courseNum = scanner.next();
                    System.out.print("Semester: ");
                    String semester = scanner.next();
                    /*System.out.print("Does this course have any prerequisites?\n" +
                            "1. Yes\n" +
                            "2. No\n" +
                            "Enter Selection Here: ");
                    String preReq*/
                    switch (addOp) {
                        case 1:
                            student.addCoursesTaken(new Course(name, CRN, credits, courseNum, semester, null));
                            break;
                        case 2:
                            student.addCurrentCourses(new Course(name, CRN, credits, courseNum, semester, null));
                            break;
                        case 3:
                            student.addCoursesPlanned(new Course(name, CRN, credits, courseNum, semester, null));
                            break;
                    }

                } else if (option == 4) {
                    // TODO : Dan Input Transcript
                    System.out.println("Please enter file path: ");
                    String file = scanner.next();
                    student.setTranscript(new Transcript(file));
                    System.out.println(student.getTranscript().toString());
                }
            }
        }
        System.out.println("Thank you for using Class Planner");
    }
}
