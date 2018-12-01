package ttps.spring.controllers.billboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.SubscriptionDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Billboard;
import ttps.spring.model.Publication;
import ttps.spring.model.Subscription;
import ttps.spring.model.User;

import java.util.Collection;
import java.util.List;

@RestController
public class BillboardController {

    @Autowired
    private BillboardDAO billboardDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SubscriptionDAO susbcriptionDao;

    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras")
    public @ResponseBody
    List<Billboard> showBillboards(@RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            //se puede tomar el usuario para ordenar las carteleras
        }

        List<Billboard> billboardList = billboardDAO.findAll();

        return billboardList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("carteleras/suscprito/ids")
    public @ResponseBody
    List<Integer> getSuscribedBillboardsIds(@RequestParam(value = "token") String sessionToken) {
        /*
        * Resturn a Array of the Billboards id's
        * */
        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        User user = sessionDAO.getByToken(sessionToken).getUser();
        List<Integer> billboards = susbcriptionDao.susbribedBillboardsIds(user.getId());

        return billboards;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/carteleras")
    public ResponseEntity<Billboard> create(
            @RequestParam(value = "token") String sessionToken,
            @RequestBody Billboard billboard) {

        /*
         * Get the logged user, assume the session exist for the previous 'if'.
         * */
        User user = sessionDAO.getByToken(sessionToken).getUser();

        /*
         * Call the DAO for create the new billboard with the custom business rules.
         * */
        billboard = this.billboardDAO.addNewBillboard(user, billboard);

        return new ResponseEntity<Billboard>(billboard, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}")
    public @ResponseBody
    ResponseEntity<Collection<Publication>> getPublications(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        Collection<Publication> publications = billboardDAO.getPublications(id);
        return new ResponseEntity<Collection<Publication>>(publications, HttpStatus.OK);

    }
}