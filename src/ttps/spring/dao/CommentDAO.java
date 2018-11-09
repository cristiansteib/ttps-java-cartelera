package ttps.spring.dao;


public class CommentDAO extends DAOHibernateImplementation<Comment, Integer> {

    @Override
    String getModelName() {
        return "Comment";
    }
}
