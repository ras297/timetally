package ir.maleki.sideprojects.timetally.backend.interfaces.rest.adapters;

import ir.maleki.sideprojects.timetally.application.task.ChangeTaskStatus;
import ir.maleki.sideprojects.timetally.application.task.CreateTask;
import ir.maleki.sideprojects.timetally.application.task.TaskSearch;
import ir.maleki.sideprojects.timetally.application.task.TaskService;
import ir.maleki.sideprojects.timetally.application.task.UpdateTask;
import ir.maleki.sideprojects.timetally.application.user.JpaUserRepository;
import ir.maleki.sideprojects.timetally.backend.dto.TaskDto;
import ir.maleki.sideprojects.timetally.backend.dto.mappers.TaskToDtoMapper;
import ir.maleki.sideprojects.timetally.backend.interfaces.rest.util.Responses;
import ir.maleki.sideprojects.timetally.domain.Task;
import ir.maleki.sideprojects.timetally.domain.TaskStatus;
import ir.maleki.sideprojects.timetally.domain.TaskType;
import ir.maleki.sideprojects.timetally.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserTaskController {
    private final TaskService taskService;
    private final TaskToDtoMapper mapper;
    private final JpaUserRepository userRepository;

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<Page<TaskDto>> searchTasks(
        @PathVariable Long userId,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) TaskStatus status,
        @RequestParam(required = false) TaskType type,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAfter,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdBefore,
        @PageableDefault(size = 20, sort = "createDate") Pageable pageable
    ) {
        var criteria = new TaskSearch(userId, title, status, type, createdAfter, createdBefore);
        Page<Task> tasksPage = taskService.searchTasks(criteria, pageable);
        Page<TaskDto> dtoPage = tasksPage.map(mapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

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

    @PatchMapping("/{userId}/tasks/{taskId}/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> changeTaskStatus(
        @PathVariable Long userId,
        @PathVariable Long taskId,
        @Validated @RequestBody ChangeTaskStatus request
    ) {
        Task task = taskService.changeStatus(userId, taskId, request);
        return Responses.ok(mapper.toDto(task));
    }

    @DeleteMapping("/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTask(
        @PathVariable Long userId,
        @PathVariable Long taskId
    ) {
        taskService.deleteTask(userId, taskId);
        return Responses.noContent();
    }

}
