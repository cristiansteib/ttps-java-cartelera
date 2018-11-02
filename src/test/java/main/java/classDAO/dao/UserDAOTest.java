package main.java.classDAO.dao;

import main.java.classDAO.dao.UserDAO;
import main.java.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userdao;

    @Before
    public void setUp() throws Exception {
        this.userdao = new UserDAO();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("pepe");
        user.setLastName("ninguno");
        user.setDNI("123123123");
        user.setPassword("secret");
        this.userdao.create(user);
        assertTrue(true);
    }

}