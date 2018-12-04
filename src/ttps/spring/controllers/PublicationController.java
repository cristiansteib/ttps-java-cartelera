package ttps.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ttps.spring.dao.BillboardDAO;
import ttps.spring.dao.SessionDAO;
import ttps.spring.dao.SubscriptionDAO;
import ttps.spring.errors.ForbiddenException;
import ttps.spring.model.Publication;

import java.util.Collection;

public class PublicationController {
    @Autowired
    private BillboardDAO billboardDAO;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SubscriptionDAO susbcriptionDao;

    private void assertIfUserIsAnonymous(String token) {
        //  the code is clear
        if (!sessionDAO.isValidSession(token)) {
            throw new ForbiddenException();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}/publicaciones/{id_pub}")
    public @ResponseBody
    ResponseEntity<Collection<Publication>> getPublication(
            @PathVariable("id") Integer idBillboard,
            @PathVariable("id_pub") Integer idPublication,
            @RequestParam(value = "token") String sessionToken) {

        //TODO: retornar solo los datos de la publicacion.

        this.assertIfUserIsAnonymous(sessionToken);
        Collection<Publication> publications = billboardDAO.getPublications(idBillboard);
        return new ResponseEntity<Collection<Publication>>(publications, HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/carteleras/{id}/publicaciones/{id_pub}/comentarios")
    public @ResponseBody
    ResponseEntity<Collection<Publication>> getPublicationCommentsWithResponses(
            @PathVariable("id") Integer idBillboard,
            @PathVariable("id_pub") Integer idPublication,
            @RequestParam(value = "token") String sessionToken) {

        //TODO: retornar los comentarios para la publicacion pasada por id, y retornar los commetarios con las respuestas

        this.assertIfUserIsAnonymous(sessionToken);
        Collection<Publication> publications = billboardDAO.getPublications(idBillboard);
        return new ResponseEntity<Collection<Publication>>(publications, HttpStatus.OK);

    }
}
