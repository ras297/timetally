package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.TaskStatus;
import ir.maleki.sideprojects.timetally.domain.TaskType;

import java.time.LocalDate;

public record TaskSearch(
    Long ownerId,
    String title,
    TaskStatus status,
    TaskType type,
    LocalDate createdAfter,
    LocalDate createdBefore
) {
}
