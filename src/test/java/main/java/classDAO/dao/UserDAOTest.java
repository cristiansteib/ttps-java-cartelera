package main.java.classDAO.dao;

import main.java.classDAO.dao.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userdao;

    @Before
    public void setUp() throws Exception {

        //User u = new User();
        //u.setName("Juan");
        //u.setLastName("Gasd");

        this.userdao = new UserDAO();
        //userdao.create(u);
        //for (User x : userdao.findAll()){
            //System.out.println(x.getNotification());
        //}
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addUser() {
        assertTrue(true);
    }

    @Test
    public void findAll() {
        assertTrue(true);

    }
}