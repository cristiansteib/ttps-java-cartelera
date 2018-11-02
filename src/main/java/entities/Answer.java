package main.java.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String answer;
    private Timestamp creationDate;

    @OneToOne
    private Comment comment;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    public String getAnswer() {
        return answer;
    }


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


}
