package ir.maleki.sideprojects.timetally.application.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateUser(@NotBlank @Size(min = 5, max = 20) String username,
                         @NotBlank @Size(min = 8, max = 20) String password) implements Serializable {
}
