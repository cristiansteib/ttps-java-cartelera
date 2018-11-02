package main.java.classDAO.dao;

import main.java.entities.Billboard;
import main.java.entities.Subscription;
import main.java.entities.User;

import javax.persistence.Query;


public class SubscriptionDAO extends DAOHibernateImplementation<Subscription, Integer> {


    @Override
    String getModelName() {
        return "Subscription";
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
        Query query = this.db.em.createQuery(
                "SELECT s FROM " + this.getModelName() +" s " +
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
