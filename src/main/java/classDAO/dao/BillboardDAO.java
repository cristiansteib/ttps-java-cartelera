package main.java.classDAO.dao;

import main.java.entities.Billboard;
import main.java.entities.Publication;
import main.java.entities.Subscription;
import main.java.entities.User;

public class BillboardDAO extends DAOHibernateImplementation<Billboard, Integer> {

    @Override
    String getModelName() {
        return "Billboard";
    }

    private static boolean canModify(Billboard billboard, User who) {
        return (billboard.getManagedBy().contains(who) || who.getAdmin());
    }

    public boolean addPublication(Billboard billboard, Publication publication, User who) {
        if ( canModify(billboard, who )){
            billboard.addPublication(publication);
            this.update(billboard);
            return true;
        };
        return false;
    }

    public boolean removePublication(Billboard billboard, Publication publication, User who) {
        if ( canModify(billboard, who )){
            billboard.removePublication(publication);
            this.update(billboard);
            return true;
        };
        return false;
    }

    //public Collection<User> listSuscriptors () {    }

    // public static Collection<User> listSuscriptorsFor(Billboard billboard) {   }




    public void allowEditionTo(User user) {
    }


    //public Collection<Billboard> getNotYetPublished() {    }


}
