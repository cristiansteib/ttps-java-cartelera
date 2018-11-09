package ttps.spring.dao;

public class AnswerDAO extends DAOHibernateImplementation<Answer,Integer> {

    @Override
    String getModelName() {
        return "Answer";
    }


}
