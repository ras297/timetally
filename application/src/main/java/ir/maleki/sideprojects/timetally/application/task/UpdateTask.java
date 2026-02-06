package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.TaskType;
import jakarta.validation.constraints.Size;

public record UpdateTask(
    @Size(max = 255)
    String title,

    @Size(max = 3000)
    String description,

    TaskType type
) { }
