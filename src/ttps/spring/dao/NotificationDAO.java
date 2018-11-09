package ttps.spring.dao;


public class NotificationDAO extends DAOHibernateImplementation<Notification, Integer> {

    @Override
    String getModelName() {
        return "Notification";
    }
}
