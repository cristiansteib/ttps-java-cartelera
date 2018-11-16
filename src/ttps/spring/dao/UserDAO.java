package ttps.spring.dao;

import ttps.spring.model.User;

public class UserDAO extends DaoImplementation<User, Integer> {

    public UserDAO() {
        super(User.class);
    }

}

