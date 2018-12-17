package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.Billboard;
import ttps.spring.model.Session;
import ttps.spring.model.Subscription;
import ttps.spring.model.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriptionDAO extends DaoImplementation<Subscription, Integer> {

    public SubscriptionDAO() {
        setPersistentClass(Subscription.class);
    }

    public List<Integer> susbribedBillboardsIds(Integer userId) {
        /*
        * Return a list of Id which the billboards that the user is subscribed
        * */
        String queryString = "SELECT s FROM Subscription s  WHERE user_id = :user_id";
        TypedQuery<Subscription> query = getEntityManager().createQuery(queryString, Subscription.class);
        query.setParameter("user_id", userId);
        List<Subscription> subscriptions = query.getResultList();
        List <Integer> result_list = new ArrayList<Integer>();
        for (int i = 0; i < subscriptions.size(); ++i) {
            result_list.add(subscriptions.get(i).getBillboard().getId());
        }
        return (result_list);
    }

    public Subscription addSubscriber(Billboard billboard, User user, boolean facebook, boolean email, boolean sms) {

        Subscription suscription = new Subscription();
        suscription.setUser(user);
        suscription.setBillboard(billboard);
        if (facebook) suscription.setFacebook(true);
        if (email) suscription.setEmail(true);
        if (sms) suscription.setSms(true);
        return this.update(suscription);
    }

    public Subscription addSubscriber(Billboard billboard, User user) {
        if (billboard != null && user != null) {
            Subscription subscription = this.getByUserIdAndBillboardId(user.getId(), billboard.getId());
            if (subscription == null) {
                return this.addSubscriber(billboard, user, false, false, false);
            }
        }
        return null;
    }

    @Transactional
    public Subscription getByUserIdAndBillboardId(Integer userId, Integer billboardId) {
        String queryString = "SELECT s FROM Subscription s WHERE user_id = :userId AND billboard_id = :billboardId";
        TypedQuery<Subscription> query = getEntityManager().createQuery(queryString, Subscription.class);
        query.setParameter("userId", userId);
        query.setParameter("billboardId", billboardId);
        List result = query.getResultList();
        if (result.size() > 0) {
            return (Subscription) result.get(0);
        }
        return null;
    }

    public boolean removeSubscriber(Billboard billboard, User user) {
        try {
            Subscription subscription = this.getByUserIdAndBillboardId(user.getId(), billboard.getId());
            if (subscription != null){
                System.out.println("borrando");
                Query query = this.getEntityManager().createQuery(
                        "DELETE FROM Subscription s " +
                                "WHERE s.id = :id"
                );
                query.setParameter("id",subscription.getId());
                query.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("exploto " + e.toString());
            return false;
        }
    }

    public List<Billboard> suscribedBillboards(User user) {
        try {
            String queryString =
            "SELECT IF( CHAR_LENGTH(b.title) < 15, b.title, CONCAT(SUBSTRING(b.title,1,20),' ...')) as title, b.id, b.creationDate, b.description, b.deleted " +
                    "FROM Billboard b INNER JOIN Subscription s on s.billboard_id = b.id " +
                    "WHERE s.user_id = :user_id AND (b.deleted = FALSE OR b.deleted = NULL)";
            Query query = getEntityManager().createNativeQuery(queryString,Billboard.class);
            query.setParameter("user_id", user.getId());
            List<Billboard> result = query.getResultList();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }
}
