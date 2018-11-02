package main.java.classDAO.dao;

import main.java.entities.Answer;

public class AnswerDAO extends DAOHibernateImplementation<Answer,Integer> {

    @Override
    String getModelName() {
        return "Answer";
    }
}
