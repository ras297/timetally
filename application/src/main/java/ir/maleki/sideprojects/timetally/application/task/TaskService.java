package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.Task;
import ir.maleki.sideprojects.timetally.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
    private final JpaTaskRepository repository;

    public TaskService(JpaTaskRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Task createTask(CreateTask command, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        Task task = new Task();
        task.setTitle(command.title());
        task.setDescription(command.description());
        task.setStatus(command.status());
        task.setType(command.type());
        task.setUser(user);

        return repository.save(task);
    }
}
