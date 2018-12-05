package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PublicationDAO extends DaoImplementation<Publication, Integer>{

    public PublicationDAO(){
        setPersistentClass(Publication.class);
    }

    public void addComment(Comment comment, Integer pub_id){
        Publication publication = this.getById(pub_id);
        publication.addComment(comment);
    }

    public void removeComment(Comment comment, Integer pub_id){
        Publication publication = this.getById(pub_id);
        publication.removeComment(comment);
    }

    public List<Comment> getCommentForPublication(Integer pub_id){
        try {
            String queryString = "SELECT c FROM Comment c WHERE publication_id = :pubId";
            TypedQuery<Comment> query = getEntityManager().createQuery(queryString, Comment.class);
            query.setParameter("pubId", pub_id);
            List<Comment> result = query.getResultList();
            return (result);
        } catch (NoResultException e) {
            return null;
        }

    }
}
