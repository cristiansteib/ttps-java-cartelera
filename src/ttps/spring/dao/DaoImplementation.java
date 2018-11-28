package ttps.spring.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public abstract class DaoImplementation<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> persistentClass;

    public final void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }
/*
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
*/
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T getById(ID id) {
        return this.getEntityManager().find(this.getPersistentClass(), id);
    }

    public List<T> findAll() {
        return this.getEntityManager().createQuery(
                "SELECT e " +
                        "FROM " + this.getPersistentClass().getName() + " e"
        ).getResultList();
    }

    public void create(T entity) {
        this.getEntityManager().persist(entity);
    }

    public T update(T entity) {
        this.getEntityManager().merge(entity);
        return entity;
    }

    public void remove(T entity) {
        this.getEntityManager().remove(entity);
    }
}
