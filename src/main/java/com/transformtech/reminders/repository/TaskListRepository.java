package com.transformtech.reminders.repository;

import com.transformtech.reminders.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskEntity, Long> {
List<TaskEntity> findAllByIsActiveTrue();
Optional<TaskEntity> findAllByIdAndIsActiveTrue(Long id);
}
