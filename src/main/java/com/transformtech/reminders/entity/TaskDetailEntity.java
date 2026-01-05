package com.transformtech.reminders.entity;

import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
// Thiếu constructor ở entity
@Entity
@Table(name="task_detail")
public class TaskDetailEntity extends BaseEntity {
    @Column(name ="taskitem", columnDefinition = "TEXT", nullable = false)
    private String taskItem;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name ="executiondate")
    private LocalDate executionDate;
    @Column(name ="executiontime")
    private LocalTime executionTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="tasklist_id" , nullable = false)
    private TaskEntity tasklist;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column
    private Priority priority;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskItem() {
        return taskItem;
    }

    public void setTaskItem(String taskItem) {
        this.taskItem = taskItem;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public LocalTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalTime executionTime) {
        this.executionTime = executionTime;
    }

    public TaskEntity getTasklist() {
        return tasklist;
    }

    public void setTasklist(TaskEntity tasklist) {
        this.tasklist = tasklist;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
