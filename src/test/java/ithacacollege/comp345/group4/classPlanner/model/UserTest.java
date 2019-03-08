package ithacacollege.comp345.group4.classPlanner.model;

import ithacacollege.comp345.group4.classPlanner.InvalidArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorTest() {
        User user = new User("user1", "password");
        assertNotNull(user);
        assertThrows(InvalidArgumentException.class, ()-> new User("", "password"));
        assertThrows(InvalidArgumentException.class, ()-> new User("user1", ""));
        assertThrows(NullPointerException.class, ()-> new User(null, "password"));
        assertThrows(NullPointerException.class, ()-> new User("user1", null));
    }

    @Test
    void secureHashTest() throws Exception {
        String password = "password";
        // Create 2 hashes
        String hash1 = User.secureHash(password);
        String hash2 = User.secureHash(password);

        // Make sure that their passwords are not stored as plaintext
        assertNotEquals(password, hash1);
        assertNotEquals(password, hash2);

        // Make sure that the two password hashes are different, ensures randomization
        assertNotEquals(hash1, hash2);

        // Don't allow null or empty strings
        assertThrows(InvalidArgumentException.class, ()-> User.secureHash(""));
        assertThrows(InvalidArgumentException.class, ()-> User.secureHash(null));
    }

}