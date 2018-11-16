package ttps.spring.dao;

import ttps.spring.model.*;
import org.springframework.stereotype.Repository;

@Repository
public class BillboardDAO extends DaoImplementation<Billboard, Integer> {

   public BillboardDAO(){
       super(Billboard.class);
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
