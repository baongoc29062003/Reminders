package com.transformtech.reminders.repository;

import com.transformtech.reminders.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskEntity, Long> {

}
