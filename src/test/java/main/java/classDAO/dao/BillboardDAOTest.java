package main.java.classDAO.dao;

import main.java.classDAO.dao.BillboardDAO;
import main.java.entities.Billboard;
import main.java.entities.Publication;
import main.java.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillboardDAOTest {

    private Billboard billboard;
    BillboardDAO billboadDao;

    @Before
    public void setUp() throws Exception {
        this.billboard = new Billboard();
        this.billboadDao = new BillboardDAO();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void professorUserCanAddPublication() {
        User professor = new User();
        professor.setName("Julio");
        professor.setLastName("Julio2");
        this.billboard.addManagedBy(professor);

        Publication publication = new Publication();
        publication.setTitle("Publicacion");
        publication.setContent("content");
        publication.setOwner(professor);

        assertTrue(this.billboadDao.addPublication(this.billboard, publication, professor));
    }

    @Test
    public void adminUserCanAddPublication() {
        User admin = new User();
        admin.setName("admin");
        admin.setLastName("admin");
        admin.setAdmin(true);

        Publication publication = new Publication();
        publication.setTitle("Publicacion");
        publication.setContent("content");
        publication.setOwner(admin);

        assertTrue(this.billboadDao.addPublication(this.billboard, publication, admin));
    }

    @Test
    public void alumnUserCanNotAddPublication() {
        User admin = new User();
        admin.setName("admin");
        admin.setLastName("admin");
        admin.setAdmin(true);

        Publication publication = new Publication();
        publication.setTitle("Publicacion");
        publication.setContent("content");
        publication.setOwner(admin);

        User alumn = new User();
        alumn.setName("alum");
        alumn.setLastName("alum");
        alumn.setAdmin(false);

        assertFalse(this.billboadDao.addPublication(this.billboard, publication, alumn));
    }

    @Test
    public void alumnUserCanNotDeletePublication() {
        User admin = new User();
        admin.setName("admin");
        admin.setLastName("admin");
        admin.setAdmin(true);

        Publication publication = new Publication();
        publication.setTitle("Publicacion");
        publication.setContent("content");

        publication.setOwner(admin);

        User alumn = new User();
        alumn.setName("alum");
        alumn.setLastName("alum");
        alumn.setAdmin(false);

        assertFalse(this.billboadDao.removePublication(this.billboard, publication, alumn));
    }


}