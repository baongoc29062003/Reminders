package com.transformtech.reminders.spec;

import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import org.springframework.data.jpa.domain.Specification;

//Nên tạo thêm 1 package riêng để chứa mấy cái specification
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

    public static Specification<TaskDetailEntity> isActive() {
        return (root, query, cb) -> {
            return cb.equal(root.get("isActive"), true);
        };
    }
    }
