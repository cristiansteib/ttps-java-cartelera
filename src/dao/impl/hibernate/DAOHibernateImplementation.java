package dao.impl.hibernate;

import classDAO.DAO;
import entities.User;
import org.hibernate.Query;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

abstract public class DAOHibernateImplementation<T, ID> implements DAO<T, ID> {
    private DBconnection db;

    abstract String getModelName();

    DAOHibernateImplementation() {
        this.db = DBconnection.getConnection();
    }

    public T getById(Class<T> typo, ID id) {
        return this.db.em.find(typo, id);
    }

    public List<T> findAll() {
        return this.db.em.createQuery("SELECT e FROM " + this.getModelName() + " e").getResultList();
    }

    public void create(T entity) {
        this.db.etx.begin();
        this.db.em.persist(entity);
        this.db.etx.commit();
    }

    public T update(T entity) {
        this.db.etx.begin();
        this.db.em.merge(entity);
        this.db.etx.commit();
        return entity;
    }

    public void remove(T entity) {
        this.db.etx.begin();
        this.db.em.remove(entity);
        this.db.etx.commit();
    }
}
