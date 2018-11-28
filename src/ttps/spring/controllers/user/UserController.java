package ttps.spring.controllers.user;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttps.spring.model.User;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/usuario")
    public User greeting(@RequestParam(value="name", defaultValue="World") String name) {
        User user = new User();

        return user;
    }
}