package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.User;

@Repository
public class UserDAO extends DaoImplementation<User, Integer> {

    public UserDAO() {
        setPersistentClass(User.class);
    }

}

