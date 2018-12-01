package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDAO extends DaoImplementation<Notification, Integer> {

    public NotificationDAO(){
        setPersistentClass(Notification.class);
    }
}
