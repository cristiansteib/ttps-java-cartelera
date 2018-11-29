package ttps.spring.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.User;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private SessionDAO sessionDAO;

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken,
            @RequestBody User user) {

        HttpStatus httpStatus = HttpStatus.OK;
        User currentUser = null;

        if (sessionDAO.isValidSession(sessionToken)) {
            currentUser = userDao.getById(id);
            currentUser.setName(user.getName());
            System.out.println("actualizando");
            userDao.update(currentUser);

        } else {
            httpStatus = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<User>(currentUser, httpStatus);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
        User user = userDao.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}