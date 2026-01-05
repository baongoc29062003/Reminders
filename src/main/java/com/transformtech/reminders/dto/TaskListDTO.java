package com.transformtech.reminders.dto;

import lombok.Data;

import java.util.List;

public class TaskListDTO {
    private TaskDTO taskDTO;
    private List<TaskDetailDTO> taskDetails;


    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    public List<TaskDetailDTO> getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(List<TaskDetailDTO> taskDetails) {
        this.taskDetails = taskDetails;
    }
}
