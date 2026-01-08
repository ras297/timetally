package ir.maleki.sideprojects.timetally.domain.user;

import ir.maleki.sideprojects.timetally.domain.base.Entity;

import java.time.LocalDateTime;

public class User extends Entity {
    private String email;
    protected User(Long id, LocalDateTime createDate, LocalDateTime modifyDate) {
        super(id, createDate, modifyDate);
    }
}
