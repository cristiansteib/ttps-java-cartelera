package ttps.spring.controllers.billboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.model.Billboard;
import ttps.spring.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BillboardController {

    @Autowired
    private BillboardDAO billboardDao;

    @Autowired
    private SessionDAO sessionDAO;

    @GetMapping("/carteleras")
    public @ResponseBody List<Billboard> showBillboards(@RequestParam(value = "token") String sessionToken) {

        List<Billboard> billboardList = new ArrayList<>();

        if (sessionDAO.isValidSession(sessionToken)) {
            billboardList = billboardDao.findAll();
        }

        return billboardList;
    }

}