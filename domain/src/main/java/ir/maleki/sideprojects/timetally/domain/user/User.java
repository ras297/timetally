package ir.maleki.sideprojects.timetally.domain.user;

import ir.maleki.sideprojects.timetally.domain.base.Entity;

import java.time.LocalDateTime;

public class User extends Entity {
    private final String username;

    protected User(Long id, String username, LocalDateTime createDate, LocalDateTime modifyDate) {
        super(id, createDate, modifyDate);
        this.username = username;
    }

    public String username() {
        return username;
    }
}
