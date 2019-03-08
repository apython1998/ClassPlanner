package ithacacollege.comp345.group4.classPlanner.model;

public class User {

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        if (username != null && password != null) {
            this.username = username;
            this.password = password;
        } else {
            throw new NullPointerException("Null arguments invalid");
        }
    }


    /**************************** GETTERS AND SETTERS     ****************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
