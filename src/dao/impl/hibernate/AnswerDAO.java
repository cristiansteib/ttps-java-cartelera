package dao.impl.hibernate;

import entities.Answer;

public class AnswerDAO extends DAOHibernateImplementation<Answer,Integer> {

    @Override
    String getModelName() {
        return "Answer";
    }
}
