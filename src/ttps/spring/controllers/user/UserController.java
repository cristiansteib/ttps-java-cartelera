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

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestParam(value = "token") String sessionToken,
            @RequestBody User user) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        User currentUser = userDao.getById(id);
        if (currentUser != null){
            currentUser.setName(user.getName());
            currentUser.setLastName(user.getLastName());
            currentUser.setDNI(user.getDNI());
            currentUser.setAdmin(user.getAdmin());
            currentUser.setUserName(user.getUserName());
            currentUser.setPassword(user.getPassword());
            userDao.update(currentUser);
            return new ResponseEntity<User>(currentUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(currentUser, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        User user = userDao.getById(id);
        if (user != null){
            return new ResponseEntity<User>(user,  HttpStatus.OK);
        }
        else{
            return new ResponseEntity<User>(user,  HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<User> addUser(
            @RequestBody User user,
            @RequestParam(value = "token") String sessionToken) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        //TODO: falta chequear que no exista el usuario
        userDao.create(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);

    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userDao.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}