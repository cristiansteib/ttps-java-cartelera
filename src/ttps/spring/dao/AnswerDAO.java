package ttps.spring.dao;
import ttps.spring.model.*;

import org.springframework.stereotype.Repository;

@Repository
public class AnswerDAO extends DaoImplementation<Answer,Integer>{

    public AnswerDAO() {
        setPersistentClass(Answer.class);
    }


}
