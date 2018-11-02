package dao.impl.hibernate;

import entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnswerDAOTest {

    @Before
    public void setUp() throws Exception {
        User u = new User();
        u.setName("usuario1");
        User u2 = new User();
        u2.setName("usuario2");

        Comment c = new Comment();
        c.setUser(u);
        c.setText("comentario");

        Answer a = new Answer();
        a.setAnswer("una respuesta2");
        a.setComment(c);
        a.setOwner(u2);

        Publication p = new Publication();
        p.setContent("contenido");

        Billboard b = new Billboard();
        b.setDescription("cartelera de prueba");
        b.addManagedBy(u);
        b.addManagedBy(u2);


        BillboardDAO billboardDAO = new BillboardDAO();
        billboardDAO.create(b);

        PublicationDAO publicationDAO = new PublicationDAO();
        publicationDAO.addComment(c);

        CommentDAO commentDAO = new CommentDAO();
        //commentDAO.create(c);

        AnswerDAO answerDAO = new AnswerDAO();
        //answerDAO.create(a);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getModelName() {
    }
}