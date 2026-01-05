package com.transformtech.reminders.service;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.filter.TaskDetailFilter;

import java.util.List;

public interface ITaskDetailService {
    TaskDetailDTO saveTaskDetail (TaskDetailDTO taskDetailDTO);
    void deleteTaskDetail(Long[] ids);
    void deleteAllTaskDetail();
    TaskDetailDTO findById (Long id);
    List<TaskDetailDTO> findByExcutionDate();
    List<TaskDetailDTO> search(TaskDetailFilter filter);
}
