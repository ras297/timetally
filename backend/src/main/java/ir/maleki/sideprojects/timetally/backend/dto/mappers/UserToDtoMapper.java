package ir.maleki.sideprojects.timetally.backend.dto.mappers;

import ir.maleki.sideprojects.timetally.backend.dto.UserDto;
import ir.maleki.sideprojects.timetally.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto toDto(User entity) {
        return new UserDto(entity.id(), entity.username());
    }
}
