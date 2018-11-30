package ttps.spring.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.UserDAO;
import ttps.spring.model.Session;
import ttps.spring.model.User;

@RestController
public class AuthController {

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private UserDAO userDAO;

    @CrossOrigin(origins = "*")
    @PostMapping("/auth/login")
    public AuthResponse login(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {

        AuthResponse authResponse = new AuthResponse();

        /*
         * Login a user if the credentials are valid.
         * */
        User user = userDAO.login(username, password);

        if (user != null) {

            /*
             * Create a session instance, then save in the DB
             * */
            Session session = sessionDAO.buildNewSession(user);
            sessionDAO.update(session);

            authResponse.setMsg("Successful logged.");
            authResponse.setStatus("ok");
            authResponse.setToken(session.getToken());
            authResponse.setUserId(session.getUser().getId());
        } else {
            authResponse.setMsg("Invalid credentials.");
            authResponse.setStatus("error");
        }
        return authResponse;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/auth/logout")
    public AuthResponse logout(@RequestParam(value = "token") String sessionToken) {

        sessionDAO.revoke(sessionToken);

        /*
        Always responds with OK, just for security reasons.
         */
        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus("ok");
        authResponse.setMsg("Logged out.");

        return authResponse;
    }

}