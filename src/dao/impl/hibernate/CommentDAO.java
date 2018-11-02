package dao.impl.hibernate;

import entities.Comment;

public class CommentDAO extends DAOHibernateImplementation<Comment, Integer> {

    @Override
    String getModelName() {
        return "Comment";
    }
}
