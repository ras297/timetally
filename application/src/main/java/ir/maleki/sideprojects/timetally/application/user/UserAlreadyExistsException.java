package ir.maleki.sideprojects.timetally.application.user;

public class UserAlreadyExistsException extends IllegalArgumentException {
    public UserAlreadyExistsException(String username) {
        super("User " + username + " already exists");
    }
}
