package ir.maleki.sideprojects.timetally.interfaces.rest;

import ir.maleki.sideprojects.timetally.application.user.CreateUser;
import ir.maleki.sideprojects.timetally.application.user.UserDto;
import ir.maleki.sideprojects.timetally.application.user.UserService;
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
        return ResponseEntity.created(URI.create("/api/v1/users/1")).body(userService.createUser(request));
    }

}
