package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO extends DaoImplementation<Comment, Integer> {

    public CommentDAO(){
        super(Comment.class);
    }
}
