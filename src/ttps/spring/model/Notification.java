package ttps.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String facebook;
    private String email;
    private String phoneNumber;

    @OneToOne(mappedBy = "notification")
    private User user;

    @Override
    public String toString() {
        return "face: "+this.facebook + " - mail: "+this.email+" - phone: "+this.phoneNumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
