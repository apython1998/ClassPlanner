package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;

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

    public void register() {
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

    public static void main(String[] args) {
        StudentUI studentUI = new StudentUI();
        studentUI.register();
    }
}
