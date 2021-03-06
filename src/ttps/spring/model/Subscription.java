package ttps.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Subscription implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne (cascade = CascadeType.ALL)
    private User user;

    @ManyToOne (cascade = CascadeType.ALL)
    private Billboard billboard;

    private boolean facebook;
    private boolean email;
    private boolean sms;

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }

    public boolean isFacebook() {
        return facebook;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }



}
