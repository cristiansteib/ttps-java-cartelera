package dao.impl.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class DBconnection {

    private static DBconnection connection;
    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction etx;

    static DBconnection getConnection() {

        if (connection == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
            EntityManager em = emf.createEntityManager();
            EntityTransaction etx = em.getTransaction();
            connection = new DBconnection();
            System.out.println("Create DB Connection");
        }
        return connection;
    }

}
