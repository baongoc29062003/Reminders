package com.transformtech.reminders.service;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.resp.PageResp;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaskDetailService {
    TaskDetailDTO saveTaskDetail (TaskDetailDTO taskDetailDTO);
    TaskDetailDTO updateTaskDetail (TaskDetailDTO taskDetailDTO, Long id);
    void deleteTaskDetail(Long[] ids);
    void deleteAllTaskDetail();
    TaskDetailDTO findById (Long id);
    List<TaskDetailDTO> findByExcutionDate();
    PageResp<TaskDetailDTO> search(TaskDetailFilter filter, Pageable pageable);
}
