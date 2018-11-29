package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import sun.swing.StringUIClientPropertyKey;
import ttps.spring.model.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAO extends DaoImplementation<User, Integer> {

    public UserDAO() {
        setPersistentClass(User.class);
    }

    public User login(String username, String password) {
        /* Return a user instance with the given username and password, if not found return null*/
        try {
            String queryString = "SELECT u FROM User u where userName = :username AND password = :password";
            TypedQuery<User> query = getEntityManager().createQuery(queryString, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User result = query.getSingleResult();
            return (result);
        } catch (NoResultException e) {
            return null;
        }

    }
}

