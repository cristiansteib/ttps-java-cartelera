package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.Session;
import ttps.spring.model.User;
import ttps.spring.utils.RandomString;

@Repository
public class SessionDAO extends DaoImplementation<Session, Integer> {

    public SessionDAO() {
        setPersistentClass(Session.class);
    }

    public Session buildNewSession(User user){
        RandomString token = new RandomString(20);
        Session session = new Session();
        session.setToken(token.toString());
        session.setUser(user);
        return session;
    }

}

