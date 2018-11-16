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

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T getById(Class<T> typo, ID id) {
        return this.getEntityManager().find(typo, id);
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
