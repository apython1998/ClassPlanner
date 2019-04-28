package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String major;
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
        scanner.nextLine();

        System.out.println("Please Enter a Major or 'None': ");
        major = scanner.nextLine();
        while (!studentAPI.validateMajor(major) && !major.toLowerCase().equals("none")){
            System.out.println("Major does not exist!");
            System.out.println("Please Enter a Major or 'None': ");
            major = scanner.nextLine();
        }
        boolean registered = studentAPI.register(username, password);
        if(!major.toLowerCase().equals("none"))
            studentAPI.setStudentMajor(username, major);

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

    private String convertToLetterGrade(int numGrade){
        if (numGrade >= 93){
            return "A";
        } else if (numGrade >= 90){
            return "A-";
        } else if (numGrade >= 87){
            return "B+";
        }  else if (numGrade >= 83){
            return "B";
        } else if (numGrade >= 80){
            return "B-";
        }  else if (numGrade >= 77){
            return "C+";
        } else if (numGrade >= 73){
            return "C";
        }  else if (numGrade >= 70){
            return "C-";
        } else if (numGrade >= 67){
            return "D+";
        }  else if (numGrade >= 63){
            return "D";
        } else if (numGrade >= 60){
            return "D-";
        } else {
            return "F";
        }
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
                " 4 - Input Transcript\n" +
                " 5 - Generate Schedule\n" +
                " 6 - Generate Future Course Plan\n" +
                " 7 - Send a Friend Request\n" +
                " 8 - Accept a Friend Request\n" +
                " 9 - View Friends List\n" +
                " 10 - View Pending Friend Requests\n";
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
                while (option < 0 || option > 10) {
                    System.out.print("Invalid Selection\n" +
                            "Please Choose One\n" +
                            loggedInOptions +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                }
                if (option == 1) {
                    Major m = student.getMajor();
                    if(m !=  null) {
                        try {
                            List<Course> reqs = studentAPI.viewMajorRequirements(m.getTitle() + " " + m.getType());
                            List<List<Course>> chooseOnes = studentAPI.viewMajorChooseOnes(m.getTitle() + " " + m.getType());
                            System.out.println("Requirements for " + m.getTitle() + " " + m.getType() + ":");
                            for (Course req : reqs) {
                                System.out.println("\t" + req.toString());
                            }
                            System.out.println();
                            for (List<Course> chooseOne: chooseOnes) {
                                System.out.println("\tSelect one of the Following:");
                                for (Course req: chooseOne) {
                                    System.out.println("\t\t" + req.toString());
                                }
                            }
                            System.out.println();
                        } catch (InvalidArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else
                        System.out.println("You have not declared a major.");
                } else if (option == 2) {
                    // TODO : Dylan View Courses
                    System.out.print("Please choose one:\n" +
                            "1. View Past Courses\n" +
                            "2. View Current Courses\n" +
                            "3. View Planned Courses\n" +
                            "Enter Selection Here: ");
                    int courseOp = scanner.nextInt();

                    while (courseOp < 0 || courseOp > 4) {
                        System.out.print("Invalid Selection\n" +
                                "Please choose one:\n" +
                                "1. View Past Courses\n" +
                                "2. View Current Courses\n" +
                                "3. View Planned Courses\n" +
                                "Enter Selection Here: ");
                    }
                    List<Course> viewCourses;
                    switch (courseOp){
                        case 1:
                            viewCourses = studentAPI.viewTakenCourses(student.getUsername());
                            if (viewCourses != null) {
                                System.out.print("\nPast courses " + student.getUsername()
                                        + " is enrolled in:\n" + viewCourses + "\n\n");
                            } else {
                                System.out.println("You do not have any past courses entered.");
                            }
                            break;
                        case 2:
                            viewCourses = studentAPI.viewCurrentCourses(student.getUsername());
                            if (viewCourses != null) {
                                System.out.print("\nCurrent courses " + student.getUsername()
                                        + " is enrolled in:\n" + viewCourses + "\n\n");
                            } else {
                                System.out.println("You are not currently enrolled in any courses.");
                            }
                            break;
                        case 3:
                            viewCourses = studentAPI.viewPlannedCourses(student.getUsername());
                            if (viewCourses != null) {
                                System.out.print("\nFuture Courses " + student.getUsername()
                                        + " is enrolled in:\n" + viewCourses + "\n\n");
                            } else {
                                System.out.println("You do not have any planned courses.");
                            }
                            break;

                    }
                } else if (option == 3) {
                    System.out.print("Please choose one:\n" +
                            "1. Add Past Courses\n" +
                            "2. Add Current Courses\n" +
                            "3. Add Planned Courses\n" +
                            "Enter Selection Here: ");
                    int addOp = scanner.nextInt();
                    System.out.print("Enter the department and course number for the course\n" +
                            "Example Format: MATH11100\n" +
                            "Department & Number: ");
                    String name = scanner.next();
                    Map<String, Course> catalog = studentAPI.getDirectory().getCourseCatalog();
                    while (!catalog.containsKey(name)) {
                        System.out.print("Course not found.\nEnter the department and course number for the course\n" +
                                "Example Format: MATH11100\n" +
                                "Department & Number: ");
                        name = scanner.next();
                    }
                    Course course = catalog.get(name);
                    boolean success;
                    switch (addOp) {
                        case 1:
                            success = student.addTakenCourses(course);
                            if (success){
                                boolean addTranscript;
                                System.out.println("Successfully added this course to your profile.\n" +
                                        "Enter the number grade you received for this course: ");
                                int grade = scanner.nextInt();
                                String letGrade = convertToLetterGrade(grade);
                                if (grade >= 70){
                                    addTranscript = student.addToTranscript(course, letGrade, false, true);
                                } else {
                                    addTranscript = student.addToTranscript(course, letGrade, false, false);
                                }
                                if (addTranscript) {
                                    System.out.println("Course information has been added to your transcript.\n" +
                                            student.getTranscript());
                                } else {
                                    System.out.println("There was a problem adding this course to your transcript.");
                                }
                            } else {
                                System.out.println("There was a problem adding that course to your profile.");
                            }
                            break;
                        case 2:
                            success = student.addCurrentCourses(course);
                            if (success){
                                boolean addTranscript;
                                System.out.println("Successfully added the course to your profile.");
                                addTranscript = student.addToTranscript(course, "", true, false);
                                if (addTranscript) {
                                    System.out.println("Course information has been added to your transcript.\n" +
                                            student.getTranscript());
                                } else {
                                    System.out.println("There was a problem adding this course to your transcript.");
                                }
                            } else {
                                System.out.println("There was a problem adding that course to your profile.");
                            }
                            break;
                        case 3:
                            success = student.addPlannedCourses(course);
                            if (success) {
                                System.out.println("Successfully added the course to your profile.");
                            } else {
                                System.out.println("There was a problem adding that course to your profile.");
                            }
                            break;
                    }
                } else if (option == 4) {
                    System.out.println("Please enter file path: ");
                    String file = scanner.next();
                    try {
                        Transcript transcript = new Transcript(file);
                        student.setTranscript(transcript);
                        System.out.println(student.getTranscript().toString());
                    } catch (FileNotFoundException e) {
                        System.out.println("Could not find file. Please try again");
                    }
                } else if (option == 5) {
                    try {
                        Schedule schedule = studentAPI.genSchedule(student.getUsername());
                        student.setSchedule(schedule);
                        System.out.println(student.getSchedule().display());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (option == 6) {
                    System.out.println("Enter the number of credits: ");
                    int numCred = scanner.nextInt();
                    HashMap<String, List<Course>> plan = studentAPI.generateCoursePlan(student.getUsername(), 2019, Semester.Fall, numCred);
                    System.out.println(Directory.scheduleToStr(plan));
                } else if (option == 7) {
                    System.out.println(student.friendsListToString());
                    System.out.println("Enter username:");
                    boolean exists = false;
                    String friendName = "";
                    while (!exists) {
                        friendName = scanner.next();
                        if (!studentAPI.getDirectory().getStudents().containsKey(friendName)) {
                            System.out.println("Not a valid username. Try again:");
                        } else {
                            exists = true;
                        }
                    }
                    try {
                        boolean requestSent = studentAPI.addFriend(student.getUsername(), friendName);
                        if (requestSent) {
                            System.out.println("Friend request sent!");
                        } else {
                            System.out.println("Friend has been added!");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (option == 8) {
                    System.out.println(student.friendsListToString());
                    System.out.println("Enter username:");
                    boolean exists = false;
                    String friendName = "";
                    while (!exists) {
                        friendName = scanner.next();
                        if (!studentAPI.getDirectory().getStudents().containsKey(friendName)) {
                            System.out.println("Not a valid username. Try again:");
                        } else {
                            exists = true;
                        }
                    }
                    System.out.println("Would you like to accept (y/n):");
                    String confirm = "";
                    while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                        confirm = scanner.next();
                        if(!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                            System.out.println("Not a valid selection. Try again (y/n):");
                        }
                    }
                    if (confirm.equals("y")) {
                        studentAPI.acceptFriendRequest(student.getUsername(), friendName, true);
                        System.out.println("Friend added!");
                    } else {
                        studentAPI.acceptFriendRequest(student.getUsername(), friendName, false);
                        System.out.println("Request declined.");
                    }
                } else if (option == 9) {
                    String list = student.friendsListToString();
                    if (list.equals("")) {
                        System.out.println("Oh no! So lonely...");
                    } else {
                        System.out.println(list);
                    }
                } else if (option == 10) {
                    String list = student.friendRequestListToString();
                    if (list.equals("")) {
                        System.out.println("No pending requests");
                    } else {
                        System.out.println(list);
                    }
                }
            }
        }
        System.out.println("Thank you for using Class Planner");
    }
}
