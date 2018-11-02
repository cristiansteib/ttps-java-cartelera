package dao.impl.hibernate;

import entities.Billboard;
import entities.Publication;
import entities.User;
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
        alumn.setName("admin");
        alumn.setLastName("admin");
        alumn.setAdmin(true);

        assertFalse(this.billboadDao.addPublication(this.billboard, publication, alumn));

    }
}