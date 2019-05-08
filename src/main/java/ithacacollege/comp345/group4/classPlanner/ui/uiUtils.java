package ithacacollege.comp345.group4.classPlanner.ui;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.Scanner;

public class uiUtils {

    /**
     * Min and max must be equal if there is only one option to select
     * @param scanner
     * @param optionsText
     * @param min
     * @param max
     * @return the int option
     */
    public static int getIntOption(Scanner scanner, String optionsText, int min, int max) {
        if (min > max) {
            throw new InvalidArgumentException("Minimum Option must be less than or equal to the Max option");
        } else if (scanner == null || optionsText == null) {
            throw new InvalidArgumentException("Must pass in a valid scanner and string");
        } else if (min == max) { //Case if there is only one selection that can be made
            boolean valid = false;
            int option = 0;
            while (!valid) {
                try {
                    System.out.print("Please Choose One\n" +
                            optionsText +
                            "Enter Selection Here: ");
                    option = scanner.nextInt();
                    if (option == min) {
                        valid = true;
                    } else {
                        System.out.println("Invalid Selection");
                    }
                } catch (Exception e) {
                    System.out.println("You must enter a valid number");
                    scanner.nextLine();
                }
            }
            return option;
        } else { //Case if there are multiple options
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

    /**
     * Get grade input from users
     * @param scanner
     * @param userPrompt
     * @param min
     * @param max
     * @return
     */
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

    /**
     * Get number of credits from a user (integers only) - half credits not supported
     * @param scanner
     * @param userPrompt
     * @param min
     * @param max
     * @return
     */
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

    /**
     * Allow for major input to be case insensitive, but reformat to fit the system's major catalog
     * @param major
     * @return
     */
    public static String cleanMajorString(String major) {
        if (major == null || major.trim().equals("")) {
            throw new InvalidArgumentException("Major to check must not be null or empty!");
        }
        major = major.toLowerCase();
        String[] majorTokenized = major.trim().split(" "); // Get rid of whitespace
        String cleanMajor = major;
        try {
            for (int i=0; i < majorTokenized.length-1; i++) { // For every word except the last which should be the type (BA/BS/BFA)
                majorTokenized[i] = Character.toUpperCase(majorTokenized[i].charAt(0)) + majorTokenized[i].substring(1); //Capitalize the first letter
            }
            majorTokenized[majorTokenized.length-1] = majorTokenized[majorTokenized.length-1].toUpperCase(); //Capitalize the type
            cleanMajor = String.join(" ", majorTokenized);  // Set the clean major to what was input
        } catch (IndexOutOfBoundsException e) {  // If there's a problem, just don't change anything and send it as wrong to the system anyway
            System.out.println();
        }
        return cleanMajor;
    }

}
