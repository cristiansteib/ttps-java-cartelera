package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.Billboard;
import ttps.spring.model.Subscription;
import ttps.spring.model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
            result_list.add(subscriptions.get(i).getId());
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
        return this.addSubscriber(billboard, user, false, false, false);
    }

    private Subscription findSubscriptionByUserAndBillboard(Billboard b, User u) throws Exception {
        System.out.println("bill: "+b.getId() + " user: "+u.getId());
        Query query = this.getEntityManager().createQuery(
                "SELECT s FROM " + this.getPersistentClass().getName() +" s " +
                "WHERE s.billboard.id = :userId AND s.user.id= :billboardId"
        );
        query = query.setParameter("userId",u.getId());
        query = query.setParameter("billboardId",b.getId());

        return  (Subscription) query.getSingleResult();
    }

    public boolean removeSubscriber(Billboard billboard, User user) {
        try {
            Subscription subscription = this.findSubscriptionByUserAndBillboard(billboard, user);
            this.remove(subscription);
            return true;
        } catch (Exception e) {
            System.out.println("exploto " + e.toString());
            return false;
        }
    }
}
