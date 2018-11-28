package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import sun.swing.StringUIClientPropertyKey;
import ttps.spring.model.User;

@Repository
public class UserDAO extends DaoImplementation<User, Integer> {

    public UserDAO() {
        setPersistentClass(User.class);
    }

    public User login(String username, String password) {
        /* Return a user instance with the given username and password, if not found return null*/
        User user = new User();
        return user;
    }
}

