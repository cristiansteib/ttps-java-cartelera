package dao.impl.hibernate;

import entities.Billboard;
import entities.Publication;
import entities.User;

import java.util.Collection;

public class BillboardDAO {

    public void addPublication(Publication publication){    }

    public void removePublication(Publication publication){   }

    public Collection<User> listSuscriptors () {    }

    public static Collection<User> listSuscriptorsFor(Billboard billboard) {   }

    public void addSuscriptor(User user) {   }

    public void removeSuscriptor(User user) {    }

    public void allowEditionFor (User user) {    }

    public boolean canPublicate (Billboard billboard) {    }

    public Collection<Billboard> getNotYetPublished() {    }



}
