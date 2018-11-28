package ttps.spring.controllers.auth;

import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttps.spring.model.User;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AuthController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/auth/login")
    public AuthResponse greeting(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password) {

        AuthResponse authResponse = new AuthResponse();

        return authResponse;
    }
}