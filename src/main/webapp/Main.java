package main.webapp;

import main.java.classDAO.dao.SubscriptionDAO;
import main.java.classDAO.dao.UserDAO;
import main.java.entities.Billboard;
import main.java.entities.Notification;
import main.java.entities.Subscription;
import main.java.entities.User;



public class Main {

    public static void main(String[] args) {

        User user = new User();
        User user2 = new User();
        Billboard billboard = new Billboard();
        SubscriptionDAO s = new SubscriptionDAO();
        s.addSubscriber(billboard, user);
        s.addSubscriber(billboard,user2);
        //System.out.println(s.removeSubscriber(billboard,user));

    }

}
