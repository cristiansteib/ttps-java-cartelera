package ttps.spring.controllers.billboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Billboard;
import ttps.spring.model.Session;
import ttps.spring.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BillboardController {

    @Autowired
    private BillboardDAO billboardDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping("/carteleras")
    public @ResponseBody
    List<Billboard> showBillboards(@RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        List<Billboard> billboardList = billboardDAO.findAll();

        return billboardList;
    }

    @PostMapping("/carteleras")
    public ResponseEntity<Billboard> create(
            @RequestParam(value = "token") String sessionToken,
            @RequestBody Billboard billboard) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        /*
         * Get the logged user, assume the session exist for the previous 'if'.
         * */
        User user = sessionDAO.getByToken(sessionToken).getUser();
        System.out.println(user.getId());

        /*
         * Call the DAO for create the new billboard with the custom business rules.
         * */
        this.billboardDAO.addNewBillboard(user, billboard);

        return new ResponseEntity<Billboard>(billboard, HttpStatus.OK);
    }

}