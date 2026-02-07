package ir.maleki.sideprojects.timetally.domain;

import jakarta.persistence.Column;
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

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

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

    public void changeStatus(TaskStatus newStatus) {
        if(status == null) {
            status = newStatus;
            return;
        }
        if (status == newStatus) {
            throw new IllegalStateException("Already changed status to " + newStatus);
        }
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Cannot change task status from " + status + " to " + newStatus);
        }
        this.status = newStatus;
    }

    public Long ownerId() {
        return ownerId;
    }

    public User owner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
        this.ownerId = user.id();
    }
}
