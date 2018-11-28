package ttps.spring.test.java;

import org.springframework.beans.factory.annotation.Autowired;
import ttps.spring.dao.*;
import ttps.spring.model.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Test
    public void addUser() {

        UserDAO userdao = new UserDAO();
        User user = new User();
        user.setName("pepe");
        user.setLastName("ninguno");
        user.setDNI("123123123");
        user.setPassword("secret");
        userdao.create(user);
        assertTrue(true);
    }

}