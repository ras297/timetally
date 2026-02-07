package ir.maleki.sideprojects.timetally.backend.dto;

import ir.maleki.sideprojects.timetally.domain.TaskStatus;
import ir.maleki.sideprojects.timetally.domain.TaskType;

public record TaskDto(
    Long id,
    String title,
    String description,
    TaskType type,
    TaskStatus status,
    Long ownerId
) implements Dto {}
