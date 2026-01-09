package com.transformtech.reminders.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskOverViewDTO {
    private long totalTaskItem;
    List<TaskListDTO> taskListDTOS;
}
