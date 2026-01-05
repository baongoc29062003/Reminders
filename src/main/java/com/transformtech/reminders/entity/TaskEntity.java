package com.transformtech.reminders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
// Thiếu constructor ở entity
@Entity
@Table(name ="task_list")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends BaseEntity {
    @Column
    private String name;

    @OneToMany(mappedBy = "tasklist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskDetailEntity> taskDetails;

}
