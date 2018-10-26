package main.webapp;

import dao.impl.hibernate.UserDAOHibImpl;
import entities.User;

public class Main {

    public static void main(String[] args) {
        User u = new User();
        u.setName("Pepe");
        u.setLastName("Garcia");

        UserDAOHibImpl dao = new UserDAOHibImpl();
        dao.persistir(u);

        dao.find(User.class,1);
    }

}
