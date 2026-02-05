package ir.maleki.sideprojects.timetally.interfaces.rest.adapters;

import ir.maleki.sideprojects.timetally.application.user.CreateUser;
import ir.maleki.sideprojects.timetally.application.user.UserDto;
import ir.maleki.sideprojects.timetally.application.user.UserService;
import ir.maleki.sideprojects.timetally.domain.user.User;
import ir.maleki.sideprojects.timetally.interfaces.rest.util.Responses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated CreateUser request) {
        User user = userService.createUser(request);
        UserDto response = new UserDto(user.id(), user.username());
        return Responses.created(user.id(), response);
    }

}
