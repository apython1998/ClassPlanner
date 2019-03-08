package ithacacollege.comp345.group4.classPlanner.model;

public class User {

    private String username;
    private String passwordHash;

    public User() {
    }

    public User(String username, String password) {
        if (username != null && password != null) {
            this.username = username;
            this.passwordHash = secureHash(password);
        } else {
            throw new NullPointerException("Null arguments invalid");
        }
    }

    /**
     * Generates a salted password hash for secure password storage
     * USES PBKDF2
     * @param password plaintext password to be hashed
     * @return null if password is null, hashed password otherwise
     */
    private static String secureHash(String password) {
        return null;
    }


    private static boolean authenticate(String password) {
        return false;
    }

    /**************************** GETTERS AND SETTERS     ****************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }
}
