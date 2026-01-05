package com.transformtech.reminders.filter;

import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.enun.Priority;
import com.transformtech.reminders.enun.Status;
import org.springframework.data.jpa.domain.Specification;

public class TaskDetailSpecification {
    public static Specification<TaskDetailEntity> titleContains(String q) {
        return (root, query, cb) -> {
            if (q == null || q.isBlank()) return null;
            return cb.like(root.get("taskItem"), "%" + q + "%");
        };
    }

    public static Specification<TaskDetailEntity> hasStatus(Status status) {
        return (root, query, cb) -> {
            if (status == null) return null;
            return cb.equal(root.get("status"), status);
        };
    }
    public static Specification<TaskDetailEntity> hasPriority(Priority priority) {
        return (root, query, cb) -> {
            if (priority == null) return null;
            return cb.equal(root.get("priority"), priority);
        };

    }
}
