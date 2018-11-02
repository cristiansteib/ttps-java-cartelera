package main.java.classDAO.dao;

import main.java.entities.Billboard;
import main.java.entities.Subscription;
import main.java.entities.User;

public class SubscriptionDAO extends DAOHibernateImplementation<Subscription, Integer> {


    @Override
    String getModelName() {
        return "Subscription";
    }

    public boolean addSubscriptor (Billboard billboard, User user, boolean facebook, boolean email, boolean sms){
        Subscription suscription = new Subscription();
        suscription.setUser(user);
        suscription.setBillboard(billboard);
        if (facebook) suscription.setFacebook(true);
        if (email) suscription.setEmail(true);
        if (sms) suscription.setSms(true);
        this.update(suscription);
        return true;
    }

    public boolean addSubscriptor (Billboard billboard, User user){
        return this.addSubscriptor(billboard, user,false, false, false);
    }

}
