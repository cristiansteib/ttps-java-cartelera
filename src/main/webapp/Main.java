package main.webapp;

import dao.impl.hibernate.UserDAO;
import entities.User;

public class Main {

    public static void main(String[] args) {

        User u = new User();
        u.setName("Juan");
        u.setLastName("Gasd");
        UserDAO dao = new UserDAO();
        //dao.create(u);
        User usuario = dao.getById(User.class,3);
        System.out.println("el usuario es:" + usuario.getName());

    }

}
