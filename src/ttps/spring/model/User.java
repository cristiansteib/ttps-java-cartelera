package ttps.spring.model;

import com.fasterxml.jackson.annotation.JsonView;
import ttps.spring.controllers.Views;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Summary.class)
    private Integer id;
    @JsonView(Views.Public.class)
    private String username;
    @JsonView(Views.Public.class)
    private String password;
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    private String lastName;
    @JsonView(Views.Public.class)
    private String DNI;

    @OneToOne (cascade = CascadeType.PERSIST, optional = true)
    private Notification notification;

    @JsonView(Views.Public.class)
    private Boolean isAdmin = false;

    public Integer getId() {   return id;    }

    public Boolean getAdmin() { return isAdmin != null ? isAdmin : false; }

    public void setAdmin(Boolean admin) { this.isAdmin = admin; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDNI() { return DNI; }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
