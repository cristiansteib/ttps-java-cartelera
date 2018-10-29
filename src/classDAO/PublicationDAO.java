package classDAO;

import entities.Comment;
import entities.Publication;

public interface PublicationDAO extends DAO<Publication, Integer> {

    void enableComments();
    void disableComments();
    boolean addComment(Comment comment);
    boolean removeComment(Comment comment);
}
