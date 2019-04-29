package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.controller.FacultyAPI;
import ithacacollege.comp345.group4.classPlanner.model.Course;
import ithacacollege.comp345.group4.classPlanner.model.Faculty;
import ithacacollege.comp345.group4.classPlanner.model.Student;

import java.util.Scanner;

public class FacultyUI {

    private Scanner scanner;
    private FacultyAPI facultyAPI;

    public FacultyUI() {
        this.scanner = new Scanner(System.in);
        this.facultyAPI = new FacultyAPI();
    }

    public FacultyUI(FacultyAPI facultyAPI) {
        this.facultyAPI = facultyAPI;
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
        boolean registered = facultyAPI.register(username, password);
        if (registered) {
            System.out.println("Thank you for registering as a new Faculty Member " + username);
        } else {
            System.out.println("An account with that username already exists. Sorry!");
        }
    }

    /**
     * UI function to let users login
     */
    protected Faculty login() {
        String username = "";
        String password = "";
        Faculty faculty = null;
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
        faculty = facultyAPI.login(username, password);
        if (faculty != null) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Username or Password is Incorrect");
        }
        return faculty;
    }

    public void run() {
        Integer option = Integer.MAX_VALUE;
        Faculty faculty = null;
        String nullFacultyOptions = " 0 - Quit\n" +
                " 1 - Login\n" +
                " 2 - Register\n";
        String loggedInOptions = " 0 - Quit\n" +
                " 1 - Invite student to course\n";
        while (option != 0) {
            if (faculty == null) {
                System.out.print("Please choose one\n" +
                        nullFacultyOptions +
                        "Enter Selection Here: ");
                option = scanner.nextInt();
                while (option < 0 || option > 2) {
                    System.out.print("Invalid Selection\n" +
                            "Please choose one\n" +
                            nullFacultyOptions +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                }
                if (option == 1) {
                    faculty = login();
                } else if (option == 2) {
                    register();
                }
            } else {
                System.out.print("Please choose one\n" +
                        loggedInOptions +
                        "Enter Selection Here: ");
                option = scanner.nextInt();
                while (option < 0 || option > 6) {
                    System.out.print("Invalid Selection\n" +
                            "Please Choose One\n" +
                            loggedInOptions +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                }
                if (option == 1) {
                    System.out.println("Enter the username of a student to invite:");
                    String studentUsername;
                    Student s = null;
                    while(s == null) {
                        studentUsername = scanner.next();
                        s = facultyAPI.getDirectory().getStudents().get(studentUsername);
                        if(s == null)
                            System.out.println("This student does not exist. Please enter another:");
                    }
                    System.out.println("Enter a course to invite them to:");
                    String courseNum;
                    Course c = null;
                    while(c == null){
                        courseNum = scanner.next();
                        c = facultyAPI.getDirectory().getCourseCatalog().get(courseNum);
                        if(c == null)
                            System.out.println("This course does not exist. PLease enter another:");
                    }
                    facultyAPI.inviteStudentToCourse(faculty.getUsername(), s, c);
                }
            }
            System.out.println("Thank you for using Class Planner");
        }
    }

}
