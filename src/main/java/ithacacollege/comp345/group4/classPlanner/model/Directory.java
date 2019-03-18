package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public class Directory {

    private Map<String, User> users = new HashMap<>();

    public Directory() {
    }

    public Directory(Map<String, User> users) {
        this.users = users;
    }

    /**
     * Registers a new user to the directory
     * @param username
     * @param password
     * @return true if registration successful, false if username already exists
     */
    public boolean register(String username, String password) throws InvalidArgumentException {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidArgumentException("Invalid String Used");
        } else if (users.containsKey(username)) {
            return false;
        } else {
            User newUser = new User(username, password);
            users.put(newUser.getUsername(), newUser);
            return true;
        }
    }



    /**************************** GETTERS AND SETTERS     ****************************/
    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
