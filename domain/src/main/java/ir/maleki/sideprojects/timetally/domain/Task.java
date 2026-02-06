package ir.maleki.sideprojects.timetally.domain;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Task extends BaseEntity {
    private String title;
    private String description;
    private TaskType type;
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TaskType type() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus status() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User user() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
