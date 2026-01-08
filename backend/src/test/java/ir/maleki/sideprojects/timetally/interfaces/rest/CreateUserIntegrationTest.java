package ir.maleki.sideprojects.timetally.interfaces.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CreateUserIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void should_create_user_when_valid_request() {
        String email = "foo@example.com";
        String password = "12B5$oJ2g3";

        webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                          "email": "%s",
                          "password": "%s"
                        }
                        """.formatted(email, password))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CREATED)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.email").isEqualTo(email)
                .jsonPath("$.password").doesNotExist();
    }
}
