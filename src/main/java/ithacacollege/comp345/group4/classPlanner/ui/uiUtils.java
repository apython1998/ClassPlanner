package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.Scanner;

public class uiUtils {

    public static int getIntOption(Scanner scanner, String optionsText, int min, int max) {
        if (min >= max) {
            throw new InvalidArgumentException("Minimum Option must be less than the Max option");
        } else if (scanner == null || optionsText == null) {
            throw new InvalidArgumentException("Must pass in a valid scanner and string");
        } else {
            boolean valid = false;
            int option = 0;
            while (!valid) {
                try {
                    System.out.print("Please Choose One\n" +
                            optionsText +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                    if (option >= min && option <= max) {
                        valid = true;
                    } else {
                        System.out.println("Invalid Selection");
                    }
                } catch (Exception e) {
                    System.out.println("You must enter a valid number!");
                    scanner.nextLine();
                }
            }
            return option;
        }
    }

    public static int getGrade(Scanner scanner, String userPrompt, int min, int max) {
        if (min >= max) {
            throw new InvalidArgumentException("Minimum Option must be less than the Max option");
        } else if (scanner == null || userPrompt == null) {
            throw new InvalidArgumentException("Must pass in a valid scanner and string");
        } else {
            boolean valid = false;
            int grade = 0;
            while (!valid) {
                try {
                    System.out.print(userPrompt);
                    grade = scanner.nextInt();
                    if (grade >= min && grade <= max) {
                        valid = true;
                    } else {
                        System.out.println("Invalid Grade");
                    }
                } catch (Exception e) {
                    System.out.println("You must enter a valid number!");
                    scanner.nextLine();
                }
            }
            return grade;
        }
    }

    public static int getIntCredits(Scanner scanner, String userPrompt, int min, int max) {
        if (min >= max) {
            throw new InvalidArgumentException("Minimum Option must be less than the Max option");
        } else if (scanner == null || userPrompt == null) {
            throw new InvalidArgumentException("Must pass in a valid scanner and string");
        } else {
            boolean valid = false;
            int credits = 0;
            while (!valid) {
                try {
                    System.out.print(userPrompt);
                    credits = scanner.nextInt();
                    if (credits >= min && credits <= max) {
                        valid = true;
                    } else {
                        System.out.println("Invalid Number of Credits! Must be a whole number");
                    }
                } catch (Exception e) {
                    System.out.println("You must enter a valid number!");
                    scanner.nextLine();
                }
            }
            return credits;
        }
    }

    public static String cleanMajorString(String major) {
        if (major == null || major.trim().equals("")) {
            throw new InvalidArgumentException("Major to check must not be null or empty!");
        }
        major = major.toLowerCase();
        String[] majorTokenized = major.trim().split(" ");
        String cleanMajor = major;
        try {
            for (int i=0; i < majorTokenized.length-1; i++) {
                majorTokenized[i] = Character.toUpperCase(majorTokenized[i].charAt(0)) + majorTokenized[i].substring(1);
            }
            majorTokenized[majorTokenized.length-1] = majorTokenized[majorTokenized.length-1].toUpperCase();
            cleanMajor = String.join(" ", majorTokenized);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("");
        }
        return cleanMajor;
    }

}
