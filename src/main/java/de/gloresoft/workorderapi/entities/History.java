package de.gloresoft.workorderapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class History {

    @Id
    private Long id;
    private String username;
    private String projectNumber;
    private String email_Id;
    private Timestamp timestamp;
    private String action;

    public History() {
    }


    public History(Long id, String username, String projectNumber, String emailId, Timestamp timestamp, String action) {
        this.id = id;
        this.username = username;
        this.projectNumber = projectNumber;
        this.email_Id = emailId;
        this.timestamp = timestamp;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp2) {
        this.timestamp = timestamp2;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getEmailId() {
        return email_Id;
    }

    public void setEmailId(String emailId) {
        this.email_Id = emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(username, history.username) && Objects.equals(projectNumber, history.projectNumber) && Objects.equals(email_Id, history.email_Id) && Objects.equals(timestamp, history.timestamp) && Objects.equals(action, history.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, projectNumber, email_Id, timestamp, action);
    }
}
