package dao.impl.hibernate;

import classDAO.DAO;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.ParameterizedType;

abstract public class DAOHibImpl<T, ID> implements DAO<T, ID> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction etx = em.getTransaction();


    @Override
    public T getById(Class <T> typo,ID id) {
        return this.em.find(typo, id);
    }

    @Override
    public void create(T entity) {

        etx.begin();
        this.em.persist(entity);
        etx.commit();
    }

}
