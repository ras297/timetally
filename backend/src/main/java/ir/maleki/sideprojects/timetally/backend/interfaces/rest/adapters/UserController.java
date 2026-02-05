package ir.maleki.sideprojects.timetally.backend.interfaces.rest.adapters;

import ir.maleki.sideprojects.timetally.application.user.CreateUser;
import ir.maleki.sideprojects.timetally.backend.dto.UserDto;
import ir.maleki.sideprojects.timetally.application.user.UserService;
import ir.maleki.sideprojects.timetally.backend.dto.mappers.UserToDtoMapper;
import ir.maleki.sideprojects.timetally.backend.interfaces.rest.util.Responses;
import ir.maleki.sideprojects.timetally.domain.user.User;
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
    private final UserToDtoMapper mapper;

    public UserController(UserService userService, UserToDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated CreateUser request) {
        User user = userService.createUser(request);
        UserDto response = mapper.toDto(user);
        return Responses.created(user.id(), response);
    }

}
