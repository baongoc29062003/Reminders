package com.transformtech.reminders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends AbstractDTO {
    private String name;
    private int totalTaskItemsByTaskId;
}
