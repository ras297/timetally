package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTaskRepository extends JpaRepository<Task, Long> {
}
