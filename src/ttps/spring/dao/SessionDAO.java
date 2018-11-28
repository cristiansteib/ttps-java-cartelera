package ttps.spring.dao;

import org.springframework.stereotype.Repository;
import ttps.spring.model.Session;
import ttps.spring.model.User;
import ttps.spring.utils.RandomString;

import javax.persistence.TypedQuery;
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
        String queryString = "SELECT s FROM Session s where token = :token";
        TypedQuery<Session> query = getEntityManager().createQuery(queryString, Session.class);
        query.setParameter("token", token);
        List result = query.getResultList();
        return (result.size() != 0);
    }
}

