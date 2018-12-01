package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    public User retrieveUserOrForbidden(User who, Integer user_id){
        if(who.getAdmin() || who.getId().equals(user_id)) {
            return this.getById(user_id);
        }else {
            throw new ForbiddenException();
        }


    }

    public boolean userNameExist(String username) {

        Query query = this.getEntityManager().createQuery("SELECT COUNT(userName) FROM User u WHERE u.userName = :user");
        query.setParameter("user", username);
        long count = (long) query.getSingleResult();
        return (count > 0);
    }

    public List<User> retrieveUsersOrForbidden(User user) {
        if(user.getAdmin()){
            return this.findAll();
        }else {
            throw new ForbiddenException();
        }
    }
}

