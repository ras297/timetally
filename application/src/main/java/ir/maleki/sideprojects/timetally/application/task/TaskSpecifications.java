package ir.maleki.sideprojects.timetally.application.task;

import ir.maleki.sideprojects.timetally.domain.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecifications {

    public static Specification<Task> filter(TaskSearch query) {
        return (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (query.ownerId() != null) {
                predicates.add(cb.equal(root.get("ownerId"), query.ownerId()));
            }

            if (query.title() != null) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + query.title().toLowerCase() + "%"));
            }

            if (query.status() != null) {
                predicates.add(cb.equal(root.get("status"), query.status()));
            }

            if (query.type() != null) {
                predicates.add(cb.equal(root.get("type"), query.type()));
            }

            if (query.createdAfter() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createDate"), query.createdAfter().atStartOfDay()));
            }

            if (query.createdBefore() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createDate"), query.createdBefore().atTime(23, 59, 59)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
