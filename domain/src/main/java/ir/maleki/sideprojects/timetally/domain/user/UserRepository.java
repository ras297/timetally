package ir.maleki.sideprojects.timetally.domain.user;

public interface UserRepository {
    User find(long id);
    void add(User user);
    void update(User user);
}
