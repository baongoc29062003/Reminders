package com.transformtech.reminders.entity;

import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="task_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


}
