package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.Session;
import ttps.spring.model.User;
import ttps.spring.utils.RandomString;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SessionDAO extends DaoImplementation<Session, Integer> {

    public SessionDAO() {
        setPersistentClass(Session.class);
    }

    public Session buildNewSession(User user){
        RandomString token = new RandomString(30);
        Session session = new Session();
        session.setToken(token.toString());
        session.setUser(user);
        return session;
    }

    @Transactional
    public Boolean isValidSession(String token) {
        List result = this.getEntityManager().createNativeQuery(
                "SELECT * FROM Session where token = '" + token + "'"
        ).getResultList();
        return (result.size() != 0);
    }
}

