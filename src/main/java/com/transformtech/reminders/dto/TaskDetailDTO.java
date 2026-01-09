package com.transformtech.reminders.dto;



import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailDTO extends AbstractDTO {
    @NotNull(message = "Không được bỏ trống")
    private String taskItem;
    private LocalDate executionDate;
    private LocalTime executionTime;
    @NotNull
    private Long tasklistId;
    private Status status;
    private Priority priority;
    private String description;
}
