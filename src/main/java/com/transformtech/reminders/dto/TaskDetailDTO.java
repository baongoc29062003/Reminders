package com.transformtech.reminders.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TaskDetailDTO extends AbstractDTO {
    @NotNull(message = "Không được bỏ trống")
    private String taskItem;
    private LocalDate executionDate;
    private LocalTime executionTime;
    @NotNull
    private Long tasklistId;
    private String status;
    private String priority;
    private String description;


}
