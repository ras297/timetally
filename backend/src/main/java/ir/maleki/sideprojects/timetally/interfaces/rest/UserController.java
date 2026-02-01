package ir.maleki.sideprojects.timetally.interfaces.rest;

import ir.maleki.sideprojects.timetally.application.user.CreateUser;
import ir.maleki.sideprojects.timetally.application.user.UserDto;
import ir.maleki.sideprojects.timetally.application.user.UserService;
import ir.maleki.sideprojects.timetally.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUser request) {
        User user = userService.createUser(request);
        UserDto response = new UserDto(user.id(), user.username());
        return ResponseEntity.created(URI.create("/api/v1/users/1")).body(response);
    }

}
