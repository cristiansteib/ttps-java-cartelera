package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PublicationDAO extends DaoImplementation<Publication, Integer>{

    public PublicationDAO(){
        setPersistentClass(Publication.class);
    }

    public boolean addComment(Comment comment, Integer pub_id){
        Publication publication = this.getById(pub_id);
        if (publication.isAllowComments()) {
            publication.addComment(comment);
            this.update(publication);
            return true;
        } else {
            return false;
        }
    }

    public void removeComment(Comment comment, Integer pub_id){
        Publication publication = this.getById(pub_id);
        publication.removeComment(comment);
    }

    public List<Comment> getCommentForPublication(Integer pub_id){
        try {
            String queryString = "SELECT x.id, x.creationDate, x.owner_id, x.text FROM Comment as x INNER JOIN Publication_Comment pc ON x.id = pc.comments_id WHERE pc.Publication_id = :pubId";
            Query query = getEntityManager().createNativeQuery(queryString, Comment.class);
            query.setParameter("pubId", pub_id);
            List<Comment> result = query.getResultList();
            return (result);
        } catch (NoResultException e) {
            return null;
        }

    }
}
