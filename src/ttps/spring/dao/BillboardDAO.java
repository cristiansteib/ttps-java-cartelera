package ttps.spring.dao;

import ttps.spring.errors.ForbiddenException;
import ttps.spring.errors.NotFoundException;
import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class BillboardDAO extends DaoImplementation<Billboard, Integer> {

    public BillboardDAO() {
        setPersistentClass(Billboard.class);
    }



    public Billboard addNewBillboard(User user, Billboard billboard) {

        /*
         * Only admins can add a new Billboard
         * */

        if (user.getAdmin()) {

            billboard.addManagedBy(user);
            return this.update(billboard);
        } else {
            throw new ForbiddenException();
        }
    }

    private static boolean canModify(Billboard billboard, User who) {
        return (billboard.getManagedBy().contains(who) || who.getAdmin());
    }

    public boolean addPublication(Billboard billboard, Publication publication, User who) {
        if (canModify(billboard, who)) {
            billboard.addPublication(publication);
            this.update(billboard);
            return true;
        }
        return false;
    }

    public Collection<Publication> getPublications(Integer billboard_id) {

        Billboard billboard = this.getById(billboard_id);
        if (billboard != null) {
            return billboard.getPublications();
        }
        throw new NotFoundException();

    }

    public boolean removePublication(Billboard billboard, Publication publication, User who) {
        if (canModify(billboard, who)) {
            billboard.removePublication(publication);
            this.update(billboard);
            return true;
        }
        return false;
    }

    //public Collection<User> listSuscriptors () {    }
    // public static Collection<User> listSuscriptorsFor(Billboard billboard) {   }
    public void allowEditionTo(User user) {
    }
    //public Collection<Billboard> getNotYetPublished() {    }

    public List<Billboard> getSortedBySuscription(Integer userId){
        try {
            String queryString = "SELECT b.id, b.creationDate, b.description, b.title FROM Billboard b LEFT JOIN Subscription s ON s.billboard_id = b.id ORDER BY s.user_id = :user_id DESC ";
            Query query = getEntityManager().createNativeQuery(queryString);
            query.setParameter("user_id", userId);
            List<Billboard> result = query.getResultList();
            return (result);
        } catch (NoResultException e) {
            return null;
        }
    }

}
