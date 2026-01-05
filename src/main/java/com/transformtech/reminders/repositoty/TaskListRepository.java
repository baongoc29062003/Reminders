package com.transformtech.reminders.repositoty;

import com.transformtech.reminders.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskEntity, Long> {

}
