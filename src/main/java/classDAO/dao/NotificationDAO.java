package main.java.classDAO.dao;

import main.java.entities.Notification;

public class NotificationDAO extends DAOHibernateImplementation<Notification, Integer> {

    @Override
    String getModelName() {
        return "Notification";
    }
}
