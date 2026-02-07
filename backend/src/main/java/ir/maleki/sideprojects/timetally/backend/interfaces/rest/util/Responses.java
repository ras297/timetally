package ir.maleki.sideprojects.timetally.backend.interfaces.rest.util;

import ir.maleki.sideprojects.timetally.backend.dto.Dto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class Responses {

    public static <T extends Dto> ResponseEntity<T> created(Long id, T response) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
        return ResponseEntity.created(location).body(response);
    }

    public static <T extends Dto> ResponseEntity<T> ok(T response) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
        return ResponseEntity.ok().location(location).body(response);
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }
}
