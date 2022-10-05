package model;

import java.util.Objects;

public class Bug {
    private String title;
    private String assignee;
    private String publisher;
    private boolean fixed;
    private String severityLevel;
    //private enum SeverityLevel {
    //    LOW,
    //    MEDIUM,
    //    HIGH }

    public Bug(String title, String assignee, String publisher, String severityLevel) {
        this.title = title;
        this.assignee = assignee;
        this.publisher = publisher;
        this.fixed = false;
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

    public void fixBug() {
        this.fixed = true;

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
        return fixed == bug.fixed && title.equals(bug.title) && assignee.equals(bug.assignee)
                && publisher.equals(bug.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, assignee, publisher, fixed);
    }
}


