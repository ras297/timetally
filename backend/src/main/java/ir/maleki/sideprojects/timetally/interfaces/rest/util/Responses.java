package ir.maleki.sideprojects.timetally.interfaces.rest.util;

import ir.maleki.sideprojects.timetally.application.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Responses {

    public static ResponseEntity<UserDto> created(Long id, UserDto response) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
        return ResponseEntity.created(location).body(response);
    }
}
