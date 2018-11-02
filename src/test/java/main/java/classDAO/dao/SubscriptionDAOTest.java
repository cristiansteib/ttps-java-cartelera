package main.java.classDAO.dao;

import main.java.entities.Billboard;
import main.java.entities.Subscription;
import main.java.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubscriptionDAOTest {
    SubscriptionDAO subscriptionDAO;

    @Before
    public void setUp() throws Exception {
        this.subscriptionDAO = new SubscriptionDAO();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addSubscriptor() {
        User user = new User();
        Billboard billboard = new Billboard();
        assertTrue(this.subscriptionDAO.addSubscriptor(billboard, user));
    }

    @Test
    public void addSubscriptorWithParams() {
        User user = new User();
        Billboard billboard = new Billboard();
        assertTrue(this.subscriptionDAO.addSubscriptor(billboard, user, true, false, true));
    }


}