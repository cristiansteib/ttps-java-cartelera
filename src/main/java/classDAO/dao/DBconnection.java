package main.java.classDAO.dao;

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
            connection = new DBconnection();
            connection.emf = Persistence.createEntityManagerFactory("unit");
            connection.em = connection.emf.createEntityManager();
            connection.etx = connection.em.getTransaction();
        }
        return connection;
    }
}
