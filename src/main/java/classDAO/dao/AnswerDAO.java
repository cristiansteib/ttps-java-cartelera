package main.java.classDAO.dao;

import main.java.entities.Answer;
import main.java.entities.Comment;
import main.java.entities.Publication;
import main.java.entities.User;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.security.Timestamp;
import java.util.Collection;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class AnswerDAO extends DAOHibernateImplementation<Answer,Integer> {
        AnswerDAO answerDAO = new AnswerDAO();

    @Override
    String getModelName() {
        return "Answer";
    }


}
