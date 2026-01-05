package com.transformtech.reminders.entity;

import jakarta.persistence.*;

import java.util.List;
// Thiếu constructor ở entity
@Entity
@Table(name ="task_list")
public class TaskEntity extends BaseEntity {
    @Column
    private String name;

    @OneToMany(mappedBy = "tasklist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskDetailEntity> taskDetails;

    public List<TaskDetailEntity> getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(List<TaskDetailEntity> taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
