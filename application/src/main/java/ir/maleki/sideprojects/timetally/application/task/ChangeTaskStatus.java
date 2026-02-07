package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeTaskStatus(@NotNull TaskStatus status) {
}
