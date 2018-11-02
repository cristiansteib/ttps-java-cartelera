package dao.impl.hibernate;

import entities.Billboard;
import entities.Publication;
import entities.User;

import java.util.Collection;

public class BillboardDAO {

    public boolean addPublication(Publication publication, User who) {

        return true;
    }

    public void removePublication(Publication publication, User who){   }

    //public Collection<User> listSuscriptors () {    }

   // public static Collection<User> listSuscriptorsFor(Billboard billboard) {   }

    public void addSuscriptor(User user) {   }

    public void removeSuscriptor(User user) {    }

    public void allowEditionTo (User user) {    }

    //public boolean canPublicate (Billboard billboard) {    }

    //public Collection<Billboard> getNotYetPublished() {    }



}
