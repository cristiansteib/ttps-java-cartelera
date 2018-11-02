package main.java.classDAO.dao;

import main.java.classDAO.dao.BillboardDAO;
import main.java.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnswerDAOTest {

    @Before
    public void setUp() throws Exception {

        User u = new User();
        u.setName("uu");
        User u2 = new User();
        u2.setName("uu2");

        Comment c = new Comment();
        c.setUser(u);
        c.setText("comentario");

        Answer a = new Answer();
        a.setAnswer("una respuesta2");
        a.setComment(c);
        a.setOwner(u2);

        Publication p = new Publication();
        p.setContent("contenidoooo");

        Billboard b = new Billboard();
        b.setDescription("cartelera de prueba");
        b.addManagedBy(u);

        Billboard b2 =  new Billboard();
        BillboardDAO billboardDAO = new BillboardDAO();
        b2 = billboardDAO.getById(Billboard.class, 7);
        b2.addPublication(p);
        billboardDAO.create(b2);

        /*

        PublicationDAO publicationDAO = new PublicationDAO();
        publicationDAO.addComment(c);

        CommentDAO commentDAO = new CommentDAO();
        //commentDAO.create(c);

        AnswerDAO answerDAO = new AnswerDAO();
        //answerDAO.create(a);

        Subscription s = new Subscription();
        s.setEmail(true);


        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        subscriptionDAO.create(s);
    */
        /*******************/
        //b.addPublication(p);
        //billboardDAO.create(b);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getModelName() {
    }
}