package ir.maleki.sideprojects.timetally.backend.dto.mappers;

import ir.maleki.sideprojects.timetally.backend.dto.TaskDto;
import ir.maleki.sideprojects.timetally.domain.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskToDtoMapper implements Mapper<Task, TaskDto> {

    @Override
    public TaskDto toDto(Task entity) {
        return new TaskDto(entity.id(),
            entity.title(),
            entity.description(),
            entity.type(),
            entity.status(),
            entity.ownerId());
    }
}
