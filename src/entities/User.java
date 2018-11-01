package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;
    private String password;
    private String DNI;

    @OneToOne (cascade = CascadeType.PERSIST, optional = true)
    private Notification notification;

    private Boolean isAdmin;

    public Integer getId() {   return id;    }

    public Boolean getAdmin() { return isAdmin; }

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



}
