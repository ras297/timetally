package ir.maleki.sideprojects.timetally.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task extends BaseEntity {
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

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

    public User owner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
}
