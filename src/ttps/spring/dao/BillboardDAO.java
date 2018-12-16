package ttps.spring.dao;

import ttps.spring.errors.ForbiddenException;
import ttps.spring.errors.NotFoundException;
import ttps.spring.model.*;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.*;

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

    public List<User> listSuscriptorsFor(Billboard billboard) {
        String queryString = "SELECT s.id, s.email, s.facebook, s.sms, s.user_id, s.billboard_id FROM Subscription s WHERE s.billboard_id = :bid";
        Query query = getEntityManager().createNativeQuery(queryString, Subscription.class);
        query.setParameter("bid", billboard.getId());
        List<Subscription> subscriptions = query.getResultList();
        List<User> result_list = new ArrayList<User>();
        for (int i = 0; i < subscriptions.size(); ++i) {
            result_list.add(subscriptions.get(i).getUser());
        }
        return (result_list);
    }

    private static boolean canModify(Billboard billboard, User who) {
        return (who.getAdmin() || billboard.getManagedBy().contains(who));
    }

    private static void setEdition(List<Billboard> listB, User user) {
        for (Billboard b : listB) {
            b.setAllowEdit(canModify(b, user));
        }
    }

    public void setEdition(Billboard billboard, User user) {
        billboard.setAllowEdit(canModify(billboard, user));
    }


    public List<Billboard> getSortedBillboards(User user) {
        try {

            // carteleras que puedo modificar
            // cartelera que estoy suscripto
            // carteleras que estoy suscripto y ordenadas por fecha de ultima publicacion

           /* String queryString = "" +
                    "select b.id, b.creationDate, b.title, b.description, bu.managedBy_id, s.user_id" +
                    "  from Billboard b left join Billboard_User bu on b.id = bu.Billboard_id left join Subscription s on s.billboard_id = b.id" +
                    " group by b.id ";//order by bu.managedBy_id = :user_id DESC, s.user_id = :user_id DESC";*/

            String queryString = "SELECT  b.id, b.creationDate, b.title, b.description, b.deleted " +
                    "from Billboard b left join Billboard_User bu on b.id = bu.Billboard_id " +
                    "where bu.managedBy_id = :user_id AND b.deleted = false " + // primero las que puedo modificar
                    "UNION (" +
                    "SELECT  b.id, b.creationDate, b.title, b.description, b.deleted " + // Las que estoy suscripto
                    "from Billboard b left join Subscription s on s.billboard_id = b.id " +
                    "WHERE s.user_id = :user_id AND b.deleted = false)" +
                    "UNION (" +
                    "SELECT  b.id, b.creationDate, b.title, b.description , b.deleted " +
                    "FROM Billboard b WHERE b.deleted = false);";

            Query query = getEntityManager().createNativeQuery(queryString, Billboard.class);
            query.setParameter("user_id", user.getId());
            List<Billboard> result = query.getResultList();
            setEdition(result, user);
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    public void delete(Billboard billboard){
        billboard.setDeleted(true);
        this.update(billboard);
    }

}
