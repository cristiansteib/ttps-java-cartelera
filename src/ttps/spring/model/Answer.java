package ttps.spring.model;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String answer;
    private Timestamp creationDate = (new Timestamp((new Date()).getTime()));

    @ManyToOne(cascade = CascadeType.ALL)
    private Comment comment;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return owner;
    }

    public void setUser(User owner) {
        this.owner = owner;
    }

}
