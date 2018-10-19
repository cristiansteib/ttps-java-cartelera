package classDAO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Billboard implements Serializable {
    private String title;
    private String description;
    private Timestamp creationDate;
    private User user;
    private ArrayList<User> managedBy;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(ArrayList<User> managedBy) {
        this.managedBy = managedBy;
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
