package ir.maleki.sideprojects.timetally.application.user;

import ir.maleki.sideprojects.timetally.application.user.repository.JpaUserRepository;
import ir.maleki.sideprojects.timetally.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    final CreateUser COMMAND = new CreateUser("newHero", "securePassword123");

    @Mock
    private JpaUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void should_throw_exception_when_username_exists() {
        when(userRepository.existsByUsername(COMMAND.username())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {userService.createUser(COMMAND);});

        verify(userRepository, never()).saveAndFlush(any());
    }

    @Test
    void should_save_user_when_command_is_valid() {
        when(userRepository.existsByUsername(COMMAND.username())).thenReturn(false);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        userService.createUser(COMMAND);

        verify(userRepository).saveAndFlush(userCaptor.capture());
        assertEquals(COMMAND.username(), userCaptor.getValue().username());
    }
}
