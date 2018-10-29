package classDAO;

import entities.Answer;
import entities.User;

public interface AnswerDAO extends DAO<Answer,Integer> {

    User getOwner();
}
