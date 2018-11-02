package dao.impl.hibernate;

import entities.Answer;
import entities.Comment;
import entities.Publication;
import entities.User;
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
        a.setAnswer("una respuesta");
        a.setComment(c);
        a.setOwner(u2);

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.create(c);

        AnswerDAO answerDAO = new AnswerDAO();
        //answerDAO.create(a);



        //for (User x : userdao.findAll()){
        //System.out.println(x.getNotification());
        //}
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getModelName() {
    }
}