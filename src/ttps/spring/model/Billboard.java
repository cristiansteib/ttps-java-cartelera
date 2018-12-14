package ttps.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import ttps.spring.controllers.Views;

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
    @JsonView(Views.Summary.class)
    private Integer id;
    @JsonView(Views.Summary.class)
    private String title;
    @JsonView(Views.Summary.class)
    private String description;
    @JsonView(Views.Summary.class)
    private Timestamp creationDate;
    @Transient
    @JsonView(Views.Summary.class)
    private Boolean allowEdit;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> managedBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Publication> publications;

    @JsonView(Views.Summary.class)
    public Boolean getAllowEdit(){
        return this.allowEdit;
    }

    public void setAllowEdit(boolean allowEdit){
        this.allowEdit = allowEdit;
    }
    @JsonView(Views.Summary.class)
    public Integer getPubCount(){
        return publications.size();
    }

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

    public Set<User> getManagedBy() {
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
