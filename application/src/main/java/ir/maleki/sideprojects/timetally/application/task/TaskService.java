package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.common.Strings;
import ir.maleki.sideprojects.timetally.domain.Task;
import ir.maleki.sideprojects.timetally.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TaskService {
    private final JpaTaskRepository repository;

    public Page<Task> searchTasks(TaskSearch query, Pageable pageable) {
        Specification<Task> spec = TaskSpecifications.filter(query);
        return repository.findAll(spec, pageable);
    }

    @Transactional
    public Task createTask(CreateTask command, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        Task task = new Task();
        task.setTitle(command.title());
        task.setDescription(command.description());
        task.changeStatus(command.status());
        task.setType(command.type());
        task.setOwner(user);

        return repository.save(task);
    }

    @Transactional
    public Task updateTask(Long userId, Long taskId, UpdateTask command) {
        Task task = repository.findById(taskId).orElseThrow(IllegalArgumentException::new);
        if (!task.ownerId().equals(userId)) {
            throw new IllegalArgumentException("Task does not belong to user");
        }

        if (Strings.hasText(command.title())) {
            task.setTitle(command.title());
        }
        if (Strings.hasText(command.description())) {
            task.setDescription(command.description());
        }
        if (command.type() != null) {
            task.setType(command.type());
        }

        return repository.save(task);
    }

    @Transactional
    public Task changeStatus(Long userId, Long taskId, ChangeTaskStatus request) {
        Task task = repository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (!task.ownerId().equals(userId)) {
            throw new IllegalArgumentException("Task does not belong to user");
        }

        task.changeStatus(request.status());

        return repository.save(task);
    }

    @Transactional
    public void deleteTask(Long userId, Long taskId) {
        Task task = repository
            .findByIdAndOwnerId(taskId, userId)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        repository.delete(task);
    }
}
