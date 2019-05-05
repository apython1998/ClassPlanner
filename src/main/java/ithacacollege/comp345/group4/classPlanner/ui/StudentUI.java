package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.*;

import java.io.FileNotFoundException;
import java.util.*;

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

        System.out.println("Please Enter a Major or 'None':\n" +
                "Majors should be formatted\n" +
                "[Major Title] \"Major\" [Type]\n" +
                "Example: Physics Major BS");
        major = scanner.nextLine();
        major = uiUtils.cleanMajorString(major);
        while (!studentAPI.validateMajor(major) && !major.toLowerCase().equals("none")) {
            System.out.println("Major does not exist!");
            System.out.println("Please Enter a Major or 'None':\n" +
                    "Majors should be formatted\n" +
                    "[Major Title] \"Major\" [Type]\n" +
                    "Example: Physics Major BS");
            major = scanner.nextLine();
        }
        boolean registered = studentAPI.register(username, password);
        if (!major.toLowerCase().equals("none"))
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

    private String convertToLetterGrade(int numGrade) {
        if (numGrade >= 93) {
            return "A";
        } else if (numGrade >= 90) {
            return "A-";
        } else if (numGrade >= 87) {
            return "B+";
        } else if (numGrade >= 83) {
            return "B";
        } else if (numGrade >= 80) {
            return "B-";
        } else if (numGrade >= 77) {
            return "C+";
        } else if (numGrade >= 73) {
            return "C";
        } else if (numGrade >= 70) {
            return "C-";
        } else if (numGrade >= 67) {
            return "D+";
        } else if (numGrade >= 63) {
            return "D";
        } else if (numGrade >= 60) {
            return "D-";
        } else {
            return "F";
        }
    }

    public List<Course> addChooseOnes(List<List<Course>> chooseOnes){
        List<Course> returnCourses = new ArrayList<>();
        System.out.println("There are " + chooseOnes.size() + " sets of courses that are your choice.");
        for (List<Course> chooseOne : chooseOnes) {
            String optionsText = "\tSelect one of the Following:\n";
            for (int i = 0; i < chooseOne.size(); i++){
                optionsText += ("\t\t" + (i + 1) + ". " +  chooseOne.get(i).toString()) + "\n";
            }
            optionsText += ("Selection: ");
            int choiceIdx = uiUtils.getIntOption(scanner, optionsText, 1, chooseOne.size()) - 1;
            returnCourses.add(chooseOne.get(choiceIdx));
            System.out.println();
        }
        return returnCourses;
    }

    public void run() {
        Integer option = Integer.MAX_VALUE;
        Student student = null;
        String nullStudentOptions = " 0 - Quit\n" +
                " 1 - Login\n" +
                " 2 - Register\n";
        String loggedInOptions = " 0 - Quit\n" +
                " 1 - See Major Requirements\n" +
                " 2 - Change Major\n" +
                " 3 - View Courses\n" +
                " 4 - Add Courses\n" +
                " 5 - Input Transcript\n" +
                " 6 - Generate schedule\n" +
                " 7 - Generate Future Course Plan\n" +
                " 8 - Send a Friend Request\n" +
                " 9 - Accept a Friend Request\n" +
                " 10 - View Friends List\n" +
                " 11 - View Pending Friend Requests\n" +
                " 12 - View Course Invitations\n" +
                " 13 - Search Major Reqs\n" +
                " 14 - View Friend's Schedule\n";
        System.out.println("Welcome to Class Planner\n");

        while (option != 0) {
            if (student == null) {
                option = uiUtils.getIntOption(scanner, nullStudentOptions, 0, 2);
                if (option == 1) {
                    student = login();
                } else if (option == 2) {
                    register();
                }
            } else {
                option = uiUtils.getIntOption(scanner, loggedInOptions, 0, 14);

                if (option == 1) {
                    Major m = student.getMajor();
                    if (m != null) {
                        try {
                            List<Course> reqs = studentAPI.viewMajorRequirements(m.getTitle() + " " + m.getType());
                            List<List<Course>> chooseOnes = studentAPI.viewMajorChooseOnes(m.getTitle() + " " + m.getType());
                            System.out.println("Requirements for " + m.getTitle() + " " + m.getType() + ":");
                            for (Course req : reqs) {
                                System.out.println("\t" + req.toString());
                            }
                            System.out.println();
                            for (List<Course> chooseOne : chooseOnes) {
                                System.out.println("\tSelect one of the Following:");
                                for (Course req : chooseOne) {
                                    System.out.println("\t\t" + req.toString());
                                }
                            }
                            System.out.println();
                        } catch (InvalidArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else
                        System.out.println("You have not declared a major.");
                } else if (option == 2) {
                    scanner.nextLine(); //clear buffer

                    System.out.println("You're currently enrolled in the " + student.getMajor() + " program.\n" +
                            "Please enter your desired major here or 'quit' if you no longer want to switch majors:\n"+
                            "Majors should be formatted\n" +
                            "[Major Title] \"Major\" [Type]\n" +
                            "Example: Physics Major BS");
                    String major = scanner.nextLine();
                    major = uiUtils.cleanMajorString(major);
                    while (!studentAPI.validateMajor(major) && !major.toLowerCase().equals("quit")){
                        System.out.println("I'm sorry. That major is not currently supported.");
                        System.out.println("Please Enter a different Major or 'Quit': ");
                        major = scanner.nextLine();
                        major = uiUtils.cleanMajorString(major);
                    }
                    if(!major.toLowerCase().equals("quit")) {
                        studentAPI.setStudentMajor(student.getUsername(), major);
                        System.out.println("You're now enrolled in the " + major + " program.");
                    }
                } else if (option == 3) {
                    String courseOptionsString = "0. Quit\n" +
                            "1. View Past Courses\n" +
                            "2. View Current Courses\n" +
                            "3. View Planned Courses\n";
                    // TODO : Dylan View Courses
                    int courseOp = uiUtils.getIntOption(scanner, courseOptionsString, 0, 3);
                    List<Course> viewCourses;
                    switch (courseOp) {
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
                } else if (option == 4) {
                    String addCourseOptionsString =  "0. Quit\n" +
                            "1. Add Past Courses\n" +
                            "2. Add Current Courses\n" +
                            "3. Add Planned Courses\n";
                    int addOp = uiUtils.getIntOption(scanner, addCourseOptionsString, 0, 3);
                    if (addOp != 0) {
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
                                if (success) {
                                    boolean addTranscript;
                                    System.out.println("Successfully added this course to your profile");
                                    String enterGradeString = "Enter the number grade you received for this course: ";
                                    int grade = uiUtils.getGrade(scanner, enterGradeString, 0, 100);
                                    String letGrade = convertToLetterGrade(grade);
                                    if (grade >= 70) {
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
                                if (success) {
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
                    }
                } else if (option == 5) {
                    // TODO : Dan Input Transcript
                    System.out.println("Please enter file path or 'quit': ");
                    String file = scanner.next();
                    if (!file.toLowerCase().equals("quit")) {
                        try {
                            Transcript transcript = new Transcript(file);
                            student.setTranscript(transcript);
                            System.out.println(student.getTranscript().toString());
                        } catch (FileNotFoundException e) {
                            System.out.println("Could not find file. Please try again");
                        }
                    }
                } else if (option == 6) {
                    try {
                        Schedule schedule = studentAPI.genSchedule(student.getUsername());
                        student.setSchedule(schedule);
                        System.out.println(student.getSchedule().display());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (option == 7) {
                    List<Course> chooseOnes = addChooseOnes(student.getMajor().getChooseOnes());
                    String creditsString = "Enter the number of credits: ";
                    int numCred = uiUtils.getIntCredits(scanner, creditsString, 0, Integer.MAX_VALUE);
                    HashMap<String, List<Course>> plan = studentAPI.generateCoursePlan(student.getUsername(), 2019, Semester.Spring, numCred, chooseOnes);
                    System.out.println(Directory.scheduleToStr(plan));
                } else if (option == 8) {
                    System.out.println(student.friendsListToString());
                    System.out.println("Enter username (q to quit):");
                    boolean exists = false;
                    String friendName = "";
                    while (!exists) {
                        friendName = scanner.next();
                        if (!studentAPI.getDirectory().getStudents().containsKey(friendName) && !friendName.equalsIgnoreCase("q")) {
                            System.out.println("Not a valid username. Try again (q to quit):");
                        } else {
                            exists = true;
                            if (friendName.equalsIgnoreCase("q")) {
                                friendName = "q";
                            }
                        }
                    }
                    if (!friendName.equalsIgnoreCase("q")) {
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
                    }
                } else if (option == 9) {
                    if (student.getFriendRequestList().isEmpty()) {
                        System.out.println("There are no pending friend request");
                    } else {
                        System.out.println(student.friendRequestListToString());
                        System.out.println("Enter username (q to quit):");
                        String quit = "";
                        boolean exists = false;
                        String friendName = "";
                        while (!exists) {
                            friendName = scanner.next();
                            if (!studentAPI.getDirectory().getStudents().containsKey(friendName) && !friendName.equalsIgnoreCase("q")) {
                                System.out.println("Not a valid username. Try again (q to quit):");
                            } else {
                                exists = true;
                                if (friendName.equals("q")) {
                                    quit = "q";
                                }
                            }
                        }
                        if (!quit.equalsIgnoreCase("q")) {
                            System.out.println("Would you like to accept (y/n/q):");
                            String confirm = "";
                            while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("q")) {
                                confirm = scanner.next();
                                if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n") && !confirm.equalsIgnoreCase("q")) {
                                    System.out.println("Not a valid selection. Try again (y/n/q):");
                                }
                            }
                            if (confirm.equalsIgnoreCase("y")) {
                                try {
                                    studentAPI.acceptFriendRequest(student.getUsername(), friendName, true);
                                    System.out.println("Friend added!");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            } else if (confirm.equalsIgnoreCase("n")){
                                try {
                                    studentAPI.acceptFriendRequest(student.getUsername(), friendName, false);
                                    System.out.println("Request declined.");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    }
                } else if (option == 10) {
                    String list = student.friendsListToString();
                    if (list.equals("")) {
                        System.out.println("Oh no! So lonely...");
                    } else {
                        System.out.println(list);
                    }
                } else if (option == 11) {
                    String list = student.friendRequestListToString();
                    if (list.equals("")) {
                        System.out.println("No pending requests");
                    } else {
                        System.out.println(list);
                    }
                } else if (option == 12){
                    List<Course> invitations = student.getInvitations();
                    if(invitations.size() == 0)
                        System.out.println("You have no invitations!");
                    else {
                        for (int i = 0; i < invitations.size(); i++) {
                            System.out.print(i + ": ");
                            System.out.println(invitations.get(i));
                        }
                        int inv = uiUtils.getIntOption(scanner, "Select an invitation:", 0, invitations.size()-1);
                        Course invitedCourse = invitations.get(inv);
                        System.out.println("Would you like to accept this invitation? (y/n):");
                        String confirm = "";
                        while (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                            confirm = scanner.next();
                            if (!confirm.equalsIgnoreCase("y") && !confirm.equalsIgnoreCase("n")) {
                                System.out.println("Not a valid selection. Try again (y/n):");
                            }
                        }
                        if (confirm.equals("y")) {
                            studentAPI.acceptCourseInvitation(student.getUsername(), invitedCourse);
                        } else {
                            studentAPI.declineCourseInvitation(student.getUsername(), invitedCourse);
                            System.out.println("Invitation declined.");
                        }
                    }
                }
                else if(option == 13) {
                    System.out.println("Enter a prospective major or 'quit': ");
                    scanner.nextLine();//Scanner needs to throw away a newline
                    String newMajor;
                    newMajor = scanner.nextLine();
                    if (!newMajor.toLowerCase().equals("quit")) {
                        newMajor = uiUtils.cleanMajorString(newMajor);
                        while (!studentAPI.validateMajor(newMajor)) {
                            System.out.println("Major does not exist!");
                            System.out.println("Please Enter a Major or 'quit': ");
                            newMajor = scanner.nextLine();
                            if (newMajor.toLowerCase().equals("quit")) {
                                break;
                            }
                            newMajor = uiUtils.cleanMajorString(newMajor);
                        }
                        if (!newMajor.toLowerCase().equals("quit")) {
                            List<Course> courses = studentAPI.searchMajorRequirements(student.getUsername(), newMajor);
                            System.out.println("Courses you need to complete in this major:");
                            for (Course c : courses) {
                                System.out.println(c);
                            }
                        }
                    }
                } else if (option == 14) {
                    if (student.getFriendsList().isEmpty()) {
                        System.out.println("You have no friends");
                    } else {
                        System.out.println(student.friendsListToString());
                        System.out.println("Enter username or 'quit':");
                        boolean exists = false;
                        String friendName = "";
                        while (!exists) {
                            friendName = scanner.next();
                            if (friendName.toLowerCase().equals("quit")) {
                                break;
                            }
                            if (!studentAPI.getDirectory().getStudents().containsKey(friendName)) {
                                System.out.println("Not a valid username. Try again:");
                            } else {
                                exists = true;
                            }
                        }
                        try {
                            System.out.println(studentAPI.getFriendsSchedule(student.getUsername(), friendName));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        System.out.println("Thank you for using Class Planner");
    }
}
