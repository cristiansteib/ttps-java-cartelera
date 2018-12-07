package ttps.spring.controllers.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Billboard;
import ttps.spring.model.User;
import java.util.List;

@RestController
public class SubscriptionController {

    @Autowired
    private UserDAO userDao;

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

}