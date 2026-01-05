package com.transformtech.reminders.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDTO {
    private TaskDTO taskDTO;
    private List<TaskDetailDTO> taskDetails;


}
