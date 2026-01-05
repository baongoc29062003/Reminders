package com.transformtech.reminders.filter;

import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
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
