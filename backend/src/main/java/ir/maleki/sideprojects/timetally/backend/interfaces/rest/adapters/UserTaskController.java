package ir.maleki.sideprojects.timetally.backend.interfaces.rest.adapters;

import ir.maleki.sideprojects.timetally.application.task.CreateTask;
import ir.maleki.sideprojects.timetally.application.task.TaskService;
import ir.maleki.sideprojects.timetally.application.task.UpdateTask;
import ir.maleki.sideprojects.timetally.application.user.JpaUserRepository;
import ir.maleki.sideprojects.timetally.backend.dto.TaskDto;
import ir.maleki.sideprojects.timetally.backend.dto.mappers.TaskToDtoMapper;
import ir.maleki.sideprojects.timetally.backend.interfaces.rest.util.Responses;
import ir.maleki.sideprojects.timetally.domain.Task;
import ir.maleki.sideprojects.timetally.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserTaskController {
    private final TaskService taskService;
    private final TaskToDtoMapper mapper;
    private final JpaUserRepository userRepository;

    @PostMapping("/{id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> createTask(@PathVariable Long id, @Validated @RequestBody CreateTask request) {
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Task task = taskService.createTask(request, user);
        return Responses.created(task.id(), mapper.toDto(task));
    }

    @PutMapping("/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long userId, @PathVariable Long taskId,
                                              @Validated @RequestBody UpdateTask request) {
        Task task = taskService.updateTask(userId, taskId, request);
        return Responses.ok(mapper.toDto(task));
    }

}
