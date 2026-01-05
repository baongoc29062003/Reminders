package com.transformtech.reminders.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskOverViewDTO {
    private long totalTaskItem;
    List<TaskListDTO> taskListDTOS;

    public long getTotalTaskItem() {
        return totalTaskItem;
    }

    public void setTotalTaskItem(long totalTaskItem) {
        this.totalTaskItem = totalTaskItem;
    }

    public List<TaskListDTO> getTaskListDTOS() {
        return taskListDTOS;
    }

    public void setTaskListDTOS(List<TaskListDTO> taskListDTOS) {
        this.taskListDTOS = taskListDTOS;
    }
}
