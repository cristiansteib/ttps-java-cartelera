package dao.impl.hibernate;

import entities.Subscription;

public class SubscriptionDAO extends DAOHibernateImplementation<Subscription, Integer> {


    @Override
    String getModelName() {
        return "Subscription";
    }
}
