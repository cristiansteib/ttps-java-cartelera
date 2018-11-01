package dao.impl.hibernate;

import entities.Notification;

public class NotificationDAO extends DAOHibernateImplementation<Notification, Integer> {

    @Override
    public Class<Notification> getModelClass() {
        return Notification.class;
    }
}
