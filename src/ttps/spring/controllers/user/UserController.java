package ttps.spring.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.User;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private SessionDAO sessionDAO;

    @CrossOrigin(origins = "*")
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken,
            @RequestBody User user) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        User currentUser = userDao.getById(id);
        if (currentUser != null) {
            currentUser.setName(user.getName());
            currentUser.setLastName(user.getLastName());
            currentUser.setDNI(user.getDNI());
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setAdmin(user.getAdmin());
            userDao.update(currentUser);
            return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(currentUser, HttpStatus.NOT_FOUND);
        }


    }

    @CrossOrigin(origins = "*")
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id,
                                        @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }
        User user = userDao.retrieveUserOrForbidden(sessionDAO.getUserByToken(sessionToken), id);
        if (user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/usuarios")
    public ResponseEntity<User> addUser(
            @RequestBody User user,
            @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        boolean exist = userDao.userNameExist(user.getUsername());
        if (exist) {
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        } else {
            userDao.create(user);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }


    }

    @CrossOrigin(origins = "*")
    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> listUsers(
            @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }
        List<User> users = userDao.retrieveUsersOrForbidden(sessionDAO.getUserByToken(sessionToken));

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken) {
        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }
        User currentUser = userDao.getById(id);
        if (currentUser != null) {
            userDao.remove(currentUser);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

}