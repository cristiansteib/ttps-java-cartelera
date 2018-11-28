package ttps.spring.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.UserDAO;
import ttps.spring.model.User;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDao;

    @PutMapping("/usuario/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User currentUser = userDao.getById(User.class,id);
        currentUser.setName(user.getName());
        System.out.println("actualizando");
        userDao.update(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

}