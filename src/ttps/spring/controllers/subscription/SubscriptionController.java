package ttps.spring.controllers.subscription;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.controllers.Views;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.SubscriptionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Billboard;
import ttps.spring.model.User;
import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionDAO subscriptionDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private BillboardDAO billboardDAO;


    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}/suscriptores")
    public @ResponseBody
    ResponseEntity<List<User>> getSuscriptors(
            @PathVariable("id") Integer idBillboard,
            @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        Billboard bill = billboardDAO.getById(idBillboard);
        List<User> users = billboardDAO.listSuscriptorsFor(bill);

        System.out.println("suscriptores:  "+users);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("carteleras/suscripciones")
    @JsonView(Views.Summary.class)
    public @ResponseBody
    ResponseEntity<List<Billboard>> getSuscribedBillboards(@RequestParam(value = "token") String sessionToken) {

        /*
            Return billboard list, with title len = 15. is only for a sumamry view
        */


        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        User user = sessionDAO.getUserByToken(sessionToken);

        List<Billboard> billboards = subscriptionDAO.suscribedBillboards(user);
        return new ResponseEntity<List<Billboard>>(billboards, HttpStatus.OK);

    }

}