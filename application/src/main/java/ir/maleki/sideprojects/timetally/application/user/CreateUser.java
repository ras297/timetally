package ir.maleki.sideprojects.timetally.application.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUser {
    @NotBlank
    @Size(min = 5, max = 10)
    private final String username;

    @NotBlank
    @Size(min = 6)
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
