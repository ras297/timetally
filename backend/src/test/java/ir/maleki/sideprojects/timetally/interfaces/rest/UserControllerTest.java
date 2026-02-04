package ir.maleki.sideprojects.timetally.interfaces.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maleki.sideprojects.timetally.application.user.CreateUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {
    final String URI = "/api/v1/users";
    final String USERNAME = "user297";
    final String PASSWORD = "password297";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_user_when_valid_request() throws Exception {
        CreateUser request = new CreateUser(USERNAME, PASSWORD);

        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username").value(USERNAME))
            .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void should_prevent_duplicate_username() throws Exception {
        CreateUser request = new CreateUser(USERNAME, PASSWORD);

        mockMvc.perform(post(URI)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));

        mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest());
    }
}
