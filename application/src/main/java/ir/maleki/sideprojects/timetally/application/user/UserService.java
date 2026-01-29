package ir.maleki.sideprojects.timetally.application.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Transactional
    public UserDto createUser(CreateUser command) {
        return new UserDto(1L, command.username());//TODO use repository
    }
}
