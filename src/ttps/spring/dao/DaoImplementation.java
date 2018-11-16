package ttps.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
abstract public class DAOHibernateImplementation<T, ID> implements DAO<T, ID> {
    private EntityManager entityManager;

    abstract String getModelName();

    DAOHibernateImplementation() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T getById(Class<T> typo, ID id) {
        return this.db.em.find(typo, id);
    }

    public List<T> findAll() {
        return this.db.em.createQuery("SELECT e FROM " + this.getModelName() + " e").getResultList();
    }

    public T create(T entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        this.entityManager.merge(entity);
        return entity;
    }

    public void remove(T entity) {
        this.entityManager.remove(entity);
    }
}
