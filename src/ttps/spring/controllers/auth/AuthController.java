package ttps.spring.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/auth/login")
    public AuthResponse greeting(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password) {

        AuthResponse authResponse = new AuthResponse();

        User user = userDAO.login(username, password);

        if (user != null) {
            Session session = sessionDAO.buildNewSession(user);
            sessionDAO.create(session);
            authResponse.setMsg("Successful logged.");
            authResponse.setStatus("ok");
            authResponse.setToken(session.getToken());
        } else {
            authResponse.setMsg("Invalid credentials.");
            authResponse.setStatus("error");
        }

        return authResponse;
    }
}