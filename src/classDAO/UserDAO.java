package classDAO;

import entities.Billboard;
import entities.User;

import java.util.Collection;

public interface UserDAO extends DAO<User, Integer> {

    Collection<Billboard> getBillboards();





}
