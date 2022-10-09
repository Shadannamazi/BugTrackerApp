package model;

import java.util.Objects;

//Represents a bug having a title, an assignee, a publisher, a boolean to check if its fixed and
//a severity level
public class Bug {
    private String title;
    private String assignee;
    private String publisher;
    private boolean isFixed;
    /*enum SeverityLevel {
        LOW,
        MEDIUM,
        HIGH
    }*/

    //private SeverityLevel severityLevel;
    private String severityLevel;

    //REQUIRES: title, assignee, publisher, severityLevel has a non-zero length
    //EFFECTS: title of bug is set to title; assignee of bug is set to assignee;
    //publisher of bug is set to publisher; severityLevel of bug to severityLevel
    //sets the boolean fixed to false
    public Bug(String title, String assignee, String publisher, String severityLevel) {
        this.title = title;
        this.assignee = assignee;
        this.publisher = publisher;
        this.isFixed = false;
        this.severityLevel = severityLevel;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAssignee() {
        return this.assignee;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getSeverityLevel() {
        return this.severityLevel;
    }

    public boolean isFixed() {
        return this.isFixed;
    }

    //EFFECTS: sets the fixed boolean to true
    //MODIFIES: this
    public void fixBug() {
        this.isFixed = true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bug bug = (Bug) o;
        return isFixed == bug.isFixed && title.equals(bug.title) && assignee.equals(bug.assignee)
                && publisher.equals(bug.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, assignee, publisher, isFixed);
    }
}


