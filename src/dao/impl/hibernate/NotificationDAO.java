package dao.impl.hibernate;

import entities.Notification;

public class NotificationDAO extends DAOHibernateImplementation<Notification, Integer> {

    @Override
    String getModelName() {
        return "Notification";
    }
}
