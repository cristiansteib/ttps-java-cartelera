package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class PublicationDAO extends DaoImplementation<Publication, Integer>{

    public PublicationDAO(){
        super(Publication.class);
    }

    public void addComment(Comment comment){
    }

    public void removeComment(Comment comment){   }

}
