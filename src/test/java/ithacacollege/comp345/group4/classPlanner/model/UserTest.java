package ithacacollege.comp345.group4.classPlanner.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorTest() {
        User user = new User("user1", "password");
        assertNotNull(user);
        assertThrows(NullPointerException.class, ()-> new User(null, "password"));
        assertThrows(NullPointerException.class, ()-> new User("user1", null));
    }

}