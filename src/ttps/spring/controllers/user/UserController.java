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
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        if (!sessionDAO.isValidSession("tKloL80zfHbsH7EFCcvNHiJntaG8Ky")) {
            System.out.println("invalid session token");
            throw new ForbiddenException();
        }

        User currentUser = userDao.getById(id);
        currentUser.setName(user.getName());
        System.out.println("actualizando");
        userDao.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

}