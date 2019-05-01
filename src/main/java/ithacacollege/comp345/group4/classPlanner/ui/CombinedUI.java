package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.controller.FacultyAPI;
import ithacacollege.comp345.group4.classPlanner.controller.StudentAPI;
import ithacacollege.comp345.group4.classPlanner.model.Directory;

import java.util.Scanner;

public class CombinedUI {

    Directory directory;
    Scanner scanner;

    public CombinedUI() {
        this.directory = new Directory();
        this.scanner = new Scanner(System.in);
    }

    public CombinedUI(Directory directory) {
        this.directory = directory;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        Integer option = Integer.MAX_VALUE;
        String startupOptions = " 0 - Quit\n" +
                " 1 - Student Portal\n" +
                " 2 - Faculty Portal\n";
        System.out.println("Welcome to Class Planner\n");
        while (option != 0) {
            System.out.println("Are you a Student or a Faculty Member?");
            option = uiUtils.getIntOption(scanner, startupOptions, 0, 2);
            while (option < 0 || option > 2) {
                option = uiUtils.getIntOption(scanner, startupOptions, 0, 2);
            }
            if (option == 1) {
                StudentAPI studentAPI = new StudentAPI(directory);
                StudentUI studentUI = new StudentUI(studentAPI);
                studentUI.run();
            } else if (option == 2) {
                FacultyAPI facultyAPI = new FacultyAPI(directory);
                FacultyUI facultyUI = new FacultyUI(facultyAPI);
                facultyUI.run();
            }
        }
    }

    /************************** GETTERS & SETTERS ************************/
    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
