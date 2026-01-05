package com.transformtech.reminders.repositoty;

import com.transformtech.reminders.entity.TaskDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetailEntity, Long> , JpaSpecificationExecutor<TaskDetailEntity> {

    @Modifying
    @Transactional
    @Query(value = "Delete From task_detail Where tasklist_id = ?1", nativeQuery = true)
    void deleteByTaskId(Long taskId);

    List<TaskDetailEntity> findByTasklist_Id(Long id);
    int countByTasklist_Id(Long id);

    List<TaskDetailEntity> findByExecutionDate (LocalDate date);
}
