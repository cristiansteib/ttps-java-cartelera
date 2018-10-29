package classDAO;
import entities.Billboard;
import entities.Publication;
import entities.User;

import java.util.Collection;

public interface BillboardDAO extends DAO<Billboard, Integer> {

    boolean addPublication (Publication publication);

    boolean addSuscriptor (User user);

    boolean removePublication(Publication publication);

    boolean removeSuscriptor(User user);

    Collection<User> listSuscriptors();




}
