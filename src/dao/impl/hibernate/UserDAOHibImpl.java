package dao.impl.hibernate;

import entities.User;
import classDAO.UserDAO;

import javax.persistence.EntityTransaction;

public class UserDAOHibImpl extends DAOHibImpl<User, Integer> implements UserDAO {


    public void persistir(User u) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        this.em.persist(u);
        etx.commit();
    }

}
