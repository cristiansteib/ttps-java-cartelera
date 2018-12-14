package ttps.spring.controllers.billboard;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.controllers.Views;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.SubscriptionDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Billboard;
import ttps.spring.model.Publication;
import ttps.spring.model.User;

import java.util.*;

@RestController
public class BillboardController {

    @Autowired
    private BillboardDAO billboardDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SubscriptionDAO susbcriptionDao;

    private void assertIfUserIsAnonymous(String token) {
        //  the code is clear
        if (!sessionDAO.isValidSession(token)) {
            throw new ForbiddenException();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("carteleras/suscribir/{id}")
    public @ResponseBody
    List<Integer> suscribeToBillboard(
            @RequestParam(value = "token") String sessionToken,
            @PathVariable("id") Integer id) {
        /*
         * Resturn a Array of the Billboards id's
         * */

        this.assertIfUserIsAnonymous(sessionToken);
        User user = sessionDAO.getByToken(sessionToken).getUser();
        Billboard billboard = billboardDAO.getById(id);
        susbcriptionDao.addSubscriber(billboard, user);
        List<Integer> billboards = susbcriptionDao.susbribedBillboardsIds(user.getId());
        return billboards;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("carteleras/desuscribir/{id}")
    public @ResponseBody
    List<Integer> desuscribeToBillboard(
            @RequestParam(value = "token") String sessionToken,
            @PathVariable("id") Integer id) {
        /*
         * Resturn a Array of the Billboards id's
         * */

        this.assertIfUserIsAnonymous(sessionToken);
        User user = sessionDAO.getByToken(sessionToken).getUser();
        Billboard billboard = billboardDAO.getById(id);
        susbcriptionDao.removeSubscriber(billboard, user);
        List<Integer> billboards = susbcriptionDao.susbribedBillboardsIds(user.getId());
        return billboards;
    }

    @CrossOrigin(origins = "*")
    @JsonView(Views.Summary.class)
    @GetMapping("/carteleras")
    public @ResponseBody
    Collection<Billboard> showBillboards(
            @RequestParam(value = "token", defaultValue = "noToken") String sessionToken) {

        if (sessionDAO.isValidSession(sessionToken)) {
            User user = sessionDAO.getUserByToken(sessionToken);
            Collection<Billboard> billboardList = billboardDAO.getSortedBySuscription(user);
            return billboardList;
        } else {
            Collection<Billboard> billboardList = billboardDAO.findAll();
            return billboardList;
        }


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
        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }
        User user = sessionDAO.getByToken(sessionToken).getUser();

        /*
         * Call the DAO for create the new billboard with the custom business rules.
         * */
        billboard = this.billboardDAO.addNewBillboard(user, billboard);

        return new ResponseEntity<Billboard>(billboard, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}/publicaciones")
    @JsonView(Views.Summary.class)
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


    @CrossOrigin(origins = "*")
    @JsonView(Views.Summary.class)
    @GetMapping("/carteleras/{id}")
    public @ResponseBody
    ResponseEntity<Billboard> getBillboardData(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken) {

        // este medoto lo uso para cuando un usuario entra a una cartelera en particular.

        if (!sessionDAO.isValidSession(sessionToken)) {
            //
        }
        User user = sessionDAO.getUserByToken(sessionToken);

        Billboard billboard = billboardDAO.getById(id);
        billboardDAO.setEdition(billboard,user);

        if (billboard != null) {
            return new ResponseEntity<Billboard>(billboard, HttpStatus.OK);
        } else {
            return new ResponseEntity<Billboard>(billboard, HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/carteleras/{id}")
    public @ResponseBody
    ResponseEntity<Billboard> Billboard(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken) {


        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        Billboard billboard = billboardDAO.getById(id);
        User user = sessionDAO.getUserByToken(sessionToken);

        if (user.getAdmin()) {
            billboardDAO.remove(billboard);
            return new ResponseEntity<Billboard>(billboard, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Billboard>(billboard, HttpStatus.NOT_FOUND);
        }
    }
}
