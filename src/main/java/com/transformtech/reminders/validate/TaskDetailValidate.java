package com.transformtech.reminders.validate;

import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.enums.ErrorCode;
import com.transformtech.reminders.exception.ResourceNotFoundException;
import com.transformtech.reminders.repository.TaskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDetailValidate {
    @Autowired
    private TaskDetailRepository taskDetailRepository;

    public TaskDetailEntity validateTaskDetailUpdate(Long id) {
        TaskDetailEntity taskDetailEntity = taskDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.TASK_DETAIL_NOT_FOUND));
        return taskDetailEntity;
    }
}
