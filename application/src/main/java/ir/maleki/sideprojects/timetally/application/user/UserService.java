package ir.maleki.sideprojects.timetally.application.user;

import ir.maleki.sideprojects.timetally.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {
    private final JpaUserRepository userRepository;

    @Transactional
    public User createUser(CreateUser command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new UserAlreadyExistsException(command.username());
        }
        return userRepository.save(new User(command.username()));
    }
}
