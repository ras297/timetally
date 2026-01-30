package ir.maleki.sideprojects.timetally.domain.user;

import ir.maleki.sideprojects.timetally.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class User extends BaseEntity {
    @Column
    private String username;

    public User(Long id, String username, LocalDateTime createDate, LocalDateTime modifyDate) {
        super(id, createDate, modifyDate);
        this.username = username;
    }

    public User() {
    }

    public String username() {
        return username;
    }
}
