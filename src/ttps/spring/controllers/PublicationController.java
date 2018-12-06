package ttps.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.PublicationDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Comment;
import ttps.spring.model.Publication;
import ttps.spring.model.User;

import java.util.Collection;

@RestController
public class PublicationController {
    @Autowired
    private BillboardDAO billboardDAO;

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
    @GetMapping("/carteleras/{id}/publicaciones/{id_pub}")
    public @ResponseBody
    ResponseEntity<Publication> getPublication(
            @PathVariable("id") Integer idBillboard,
            @PathVariable("id_pub") Integer idPublication,
            @RequestParam(value = "token") String sessionToken) {

        //TODO: retornar solo los datos de la publicacion.
        this.assertIfUserIsAnonymous(sessionToken);
        Publication publication = publicationDAO.getById(idPublication);
        return new ResponseEntity<Publication>(publication, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}/publicaciones/{id_pub}/comentarios")
    public @ResponseBody
    ResponseEntity<Collection<Comment>> getPublicationCommentsWithResponses(
            @PathVariable("id") Integer idBillboard,
            @PathVariable("id_pub") Integer idPublication,
            @RequestParam(value = "token") String sessionToken) {

        //TODO: retornar los comentarios para la publicacion pasada por id, y retornar los commetarios con las respuestas

        this.assertIfUserIsAnonymous(sessionToken);
        Collection<Comment> comments = publicationDAO.getCommentForPublication(idPublication);
        System.out.println("los comentarios "+comments);
        return new ResponseEntity<Collection<Comment>>(comments, HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/carteleras/{idBill}/publicaciones")
    public ResponseEntity<Publication> create(
            @RequestParam(value = "token") String sessionToken,
            @PathVariable("idBill") Integer billboardId,
            @RequestBody Publication publication) {

        if (!sessionDAO.isValidSession(sessionToken)) {
            throw new ForbiddenException();
        }

        User user = sessionDAO.getUserByToken(sessionToken);
        publication.setOwner(user);
        if (billboardDAO.addPublication(billboardDAO.getById(billboardId),publication, user)){
            return new ResponseEntity<Publication>(HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Publication>(HttpStatus.ACCEPTED);
        }
    }
}
