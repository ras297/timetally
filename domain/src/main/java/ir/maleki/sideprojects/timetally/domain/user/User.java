package ir.maleki.sideprojects.timetally.domain.user;

import ir.maleki.sideprojects.timetally.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User extends BaseEntity {
    @Column
    private String username;

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public String username() {
        return username;
    }

}
