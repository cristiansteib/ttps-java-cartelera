package main.webapp;

import main.java.classDAO.dao.UserDAO;
import main.java.entities.Notification;
import main.java.entities.User;



public class Main {

    public static void main(String[] args) {


        Notification notif = new Notification();
        notif.setEmail("mail@mail.com");

        User u = new User();
        u.setName("Juan");
        u.setLastName("Gasd");
        u.setNotification(notif);

        UserDAO userdao = new UserDAO();
        userdao.create(u);
        for (User x : userdao.findAll()){
            System.out.println(x.getNotification());
        }

    }

}
