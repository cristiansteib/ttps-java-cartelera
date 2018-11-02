package main.java.classDAO.dao;

import main.java.entities.Subscription;

public class SubscriptionDAO extends DAOHibernateImplementation<Subscription, Integer> {


    @Override
    String getModelName() {
        return "Subscription";
    }
}
