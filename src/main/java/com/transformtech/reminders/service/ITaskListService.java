package com.transformtech.reminders.service;

import com.transformtech.reminders.dto.TaskDTO;
import com.transformtech.reminders.dto.TaskListDTO;
import com.transformtech.reminders.dto.TaskOverViewDTO;

import java.util.List;

public interface ITaskListService {
    List<TaskDTO> findListTask();
    TaskDTO saveTaskList(TaskDTO taskListDTO);
    TaskDTO updateTaskList(TaskDTO taskDTO, Long id);
    void deleteTaskList(Long[] ids);
    void deleteAllTaskList();
    TaskListDTO getTaskListById(Long id);
    TaskOverViewDTO getAllTaskList();
}
