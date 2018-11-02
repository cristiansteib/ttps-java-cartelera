package dao.impl.hibernate;

import entities.Billboard;
import entities.Publication;
import entities.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class BillboardDAO extends DAOHibernateImplementation<Billboard, Integer> {

    @Override
    String getModelName() {
        return "Billboard";
    }

    public boolean addPublication(Billboard billboard, Publication publication, User who) {
        if (billboard.getManagedBy().contains(who)){
            billboard.addPublication(publication);
            this.update(billboard);
            return true;
        };
        return false;
    }

    public boolean removePublication(Billboard billboard, Publication publication, User who) {
        throw new NotImplementedException();
    }

    //public Collection<User> listSuscriptors () {    }

    // public static Collection<User> listSuscriptorsFor(Billboard billboard) {   }

    public void addSuscriptor(User user) {
    }

    public void removeSuscriptor(User user) {
    }

    public void allowEditionTo(User user) {
    }


    //public Collection<Billboard> getNotYetPublished() {    }


}
