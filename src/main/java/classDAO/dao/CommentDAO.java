package main.java.classDAO.dao;

import main.java.entities.Comment;

public class CommentDAO extends DAOHibernateImplementation<Comment, Integer> {

    @Override
    String getModelName() {
        return "Comment";
    }
}
