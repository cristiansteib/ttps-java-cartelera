package ttps.spring.controllers.Comments;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.controllers.Views;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.PublicationDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Comment;
import ttps.spring.model.Publication;
import ttps.spring.model.User;


@RestController
public class CommentsController {


    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private PublicationDAO publicationDAO;

    private void assertIfUserIsAnonymous(String token) {
        //  the code is clear
        if (!sessionDAO.isValidSession(token)) {
            throw new ForbiddenException();
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/publicaciones/{idPub}/comentarios")
    @JsonView(Views.Summary.class)
    public ResponseEntity<Comment> create(
            @RequestParam(value = "token") String sessionToken,
            @PathVariable("idPub") Integer publicationId,
            @RequestBody Comment comment) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }
        User user = sessionDAO.getUserByToken(sessionToken);
        comment.setUser(user);
        if (publicationDAO.addComment(comment, publicationId)) {
            return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Comment>(HttpStatus.FORBIDDEN);
        }
    }
}
