package com.transformtech.reminders.filter;

import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.enun.Priority;
import com.transformtech.reminders.enun.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetailFilter {
    private String q;
    private Status status;
    private Priority priority;
}
