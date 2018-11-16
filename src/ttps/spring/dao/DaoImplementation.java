package ttps.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class DaoImplementation<T, ID> implements DAO<T, ID> {
    private EntityManager entityManager;
    private Class<T> persistentClass;


    public DaoImplementation(Class<T> persistentClass) {
        this.setPersistentClass(persistentClass);
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
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
