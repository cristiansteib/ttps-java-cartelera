package ttps.spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Billboard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Timestamp creationDate;
    private Timestamp publishDate;

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<User> managedBy;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Publication> publications;

    public Billboard() {
        this.managedBy = new ArrayList<User>();
        this.publications = new ArrayList<Publication>();
    }

    public Integer getId() {
        return this.id;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Collection<User> getManagedBy() {
        return this.managedBy;
    }

    public void addManagedBy(User user) {
        this.managedBy.add(user);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
