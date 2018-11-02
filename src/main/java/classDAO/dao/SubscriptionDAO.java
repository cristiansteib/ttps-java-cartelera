package main.java.classDAO.dao;

import main.java.entities.Billboard;
import main.java.entities.Subscription;
import main.java.entities.User;


public class SubscriptionDAO extends DAOHibernateImplementation<Subscription, Integer> {


    @Override
    String getModelName() {
        return "Subscription";
    }

    public boolean addSubscriber(Billboard billboard, User user, boolean facebook, boolean email, boolean sms) {
        Subscription suscription = new Subscription();
        suscription.setUser(user);
        suscription.setBillboard(billboard);
        if (facebook) suscription.setFacebook(true);
        if (email) suscription.setEmail(true);
        if (sms) suscription.setSms(true);
        this.update(suscription);
        return true;
    }

    public boolean addSubscriber(Billboard billboard, User user) {
        return this.addSubscriber(billboard, user, false, false, false);
    }

    private Subscription findSuscriptionByUserAndBillboard(Billboard b, User u) {
        return (Subscription) this.db.em.createQuery(
                "SELECT s FROM Subscription s " +
                        "WHERE s.billboard.id = :userId AND s.user.id= :billboardId")
                .setParameter("userId", u.getId()).setParameter("billboardId", b.getId())
                .getSingleResult();
    }

    public boolean removeSubscriber(Billboard billboard, User user) {
        Subscription suscription = this.findSuscriptionByUserAndBillboard(billboard, user);
        if (suscription != null) {
            this.remove(suscription);
            return true;
        }else
            return false;
    }
}
