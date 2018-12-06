package ttps.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(value= {"managedBy", "publications"})

@Entity
public class Billboard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Timestamp creationDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<User> managedBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Publication> publications;

    public Integer getId() {
        return this.id;
    }

    public Billboard() {
        this.managedBy = new HashSet<User>();
        this.publications = new HashSet<Publication>();
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void addPublication(Publication publication) {
        this.publications.add(publication);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
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
