package ir.maleki.sideprojects.timetally.application.user;

import ir.maleki.sideprojects.timetally.application.user.repository.JpaUserRepository;
import ir.maleki.sideprojects.timetally.domain.user.User;
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
        return userRepository.saveAndFlush(new User(command.username()));
    }
}
