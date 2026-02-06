package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.TaskStatus;
import ir.maleki.sideprojects.timetally.domain.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTask(
    @NotBlank
    @Size(max = 255)
    String title,

    @Size(max = 3000)
    String description,

    @NotNull
    TaskType type,

    @NotNull
    TaskStatus status

) {
}
