package com.transformtech.reminders.repository;

import com.transformtech.reminders.entity.TaskDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetailEntity, Long> , JpaSpecificationExecutor<TaskDetailEntity> {
    int countByTasklist_IdAndIsActiveTrue(Long id);
    List<TaskDetailEntity> findByExecutionDate (LocalDate date);
    List<TaskDetailEntity> findAllByIsActiveTrue();
    List<TaskDetailEntity> findAllByTasklist_IdIn(List<Long> id);
}
