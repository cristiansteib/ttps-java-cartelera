package main.webapp;

import dao.impl.hibernate.UserDAOHibImpl;
import entities.User;

public class Main {

    public static void main(String[] args) {

        User u = new User();
        u.setName("Juan");
        u.setLastName("Garcia");

        UserDAOHibImpl dao = new UserDAOHibImpl();
        dao.create(u);
        User usuario = dao.find(User.class,3);
        System.out.println("el usuario es:" + usuario.getName());

    }

}
