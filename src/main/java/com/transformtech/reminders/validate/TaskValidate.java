package com.transformtech.reminders.validate;

import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.entity.TaskEntity;
import com.transformtech.reminders.enums.ErrorCode;
import com.transformtech.reminders.exception.ResourceNotFoundException;
import com.transformtech.reminders.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskValidate {
    @Autowired
    private TaskListRepository taskRepository;

    public TaskEntity validateTaskUpdate(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.TASK_NOT_FOUND));
        return taskEntity;
    }
}