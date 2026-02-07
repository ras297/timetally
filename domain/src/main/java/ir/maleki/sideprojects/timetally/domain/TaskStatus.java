package ir.maleki.sideprojects.timetally.domain;

import java.util.EnumSet;
import java.util.Set;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    OVERDUE;

    private Set<TaskStatus> allowedTransitions;

    static {
        PENDING.allowedTransitions = EnumSet.of(IN_PROGRESS, CANCELLED);
        IN_PROGRESS.allowedTransitions = EnumSet.of(COMPLETED, OVERDUE, CANCELLED);
        COMPLETED.allowedTransitions = EnumSet.noneOf(TaskStatus.class);
        CANCELLED.allowedTransitions = EnumSet.noneOf(TaskStatus.class);
        OVERDUE.allowedTransitions = EnumSet.of(IN_PROGRESS, COMPLETED, CANCELLED);
    }

    public boolean canTransitionTo(TaskStatus target) {
        return allowedTransitions.contains(target);
    }
}
