package ir.maleki.sideprojects.timetally.application.user;

public class CreateUser {
    private final String username;
    private final String password;

    public CreateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
