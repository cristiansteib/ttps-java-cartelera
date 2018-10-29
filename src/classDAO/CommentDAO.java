package classDAO;

import entities.Answer;
import entities.Comment;
import entities.User;

public interface CommentDAO extends DAO<Comment, Integer> {

    User getOwner();
    void addAnswer(Answer answer);
    void removeAnswer(Answer answer);
}
