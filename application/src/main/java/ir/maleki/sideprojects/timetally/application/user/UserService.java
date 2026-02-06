package ir.maleki.sideprojects.timetally.application.user;

import ir.maleki.sideprojects.timetally.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final JpaUserRepository userRepository;

    public UserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUser command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new UserAlreadyExistsException(command.username());
        }
        return userRepository.save(new User(command.username()));
    }
}
