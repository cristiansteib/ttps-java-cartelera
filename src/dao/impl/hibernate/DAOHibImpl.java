package dao.impl.hibernate;

import classDAO.DAO;
import entities.User;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

abstract public class DAOHibImpl<T, ID> implements DAO<T, ID> {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
    EntityManager em = emf.createEntityManager();
    EntityTransaction etx = em.getTransaction();

    abstract public Class<T> getModelClass();

    @Override
    public T getById(Class <T> typo,ID id) {
        return this.em.find(typo, id);
    }

    public List<T> findAll (Class <T> clazz) {
        return em.createQuery("SELECT e FROM "+ clazz.getName()+" e").getResultList();
    }

    @Override
    public void create(T entity) {
        etx.begin();
        this.em.persist(entity);
        etx.commit();
    }
    @Override
    public T update(T entity) {
        etx.begin();
        this.em.merge(entity);
        etx.commit();
        return entity;
    }

    @Override
    public void remove(T entity) {
        etx.begin();
        this.em.remove(entity);
        etx.commit();
    }

}
