package main.java.classDAO.dao;

import main.java.classDAO.dao.BillboardDAO;
import main.java.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AnswerDAOTest {
    private AnswerDAO answerDAO;

    @Before
    public void setUp() throws Exception {
        answerDAO = new AnswerDAO();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addAnswer() {
        User creator = new User();

        Publication publication = new Publication();
        publication.setTitle("pub1");
        publication.setOwner(creator);


        Comment comment = new Comment();
        comment.setText("comentario");

        publication.addComment(comment);

        Answer answer = new Answer();
        answer.setComment(comment);

        answerDAO.create(answer);
        assertTrue(true);

    }
}