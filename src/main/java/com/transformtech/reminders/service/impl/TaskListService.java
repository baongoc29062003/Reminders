package com.transformtech.reminders.service.impl;

import com.transformtech.reminders.dto.TaskDTO;
import com.transformtech.reminders.dto.TaskListDTO;
import com.transformtech.reminders.dto.TaskOverViewDTO;
import com.transformtech.reminders.entity.TaskEntity;
import com.transformtech.reminders.mapper.TaskMapper;
import com.transformtech.reminders.repository.TaskDetailRepository;
import com.transformtech.reminders.repository.TaskListRepository;
import com.transformtech.reminders.service.ITaskListService;
import com.transformtech.reminders.validate.TaskValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskListService implements ITaskListService {
    @Autowired
    private TaskMapper taskListMapper;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    TaskDetailRepository taskDetailRepository;

    @Autowired
    private TaskValidate taskValidate;


    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findListTask() {
        List<TaskEntity> taskListEntity = taskListRepository.findAll();
        List<TaskDTO> taskListDTO = taskListMapper.toDTOs(taskListEntity);
        for (TaskDTO taskDTO : taskListDTO) {
            taskDTO.setTotalTaskItemsByTaskId(taskDetailRepository.countByTasklist_Id(taskDTO.getId()));
        }

        return taskListDTO;
    }


    @Override
    @Transactional
    public TaskDTO saveTaskList(TaskDTO taskListDTO) {
        TaskEntity taskListEntity = taskListMapper.toEntity(taskListDTO);
        taskListEntity = taskListRepository.save(taskListEntity);
        TaskDTO result = taskListMapper.toDTO(taskListEntity);
        return result;
    }

    @Override
    @Transactional
    public TaskDTO updateTaskList(TaskDTO taskDTO,Long id) {
        TaskEntity oldTask = taskValidate.validateTaskUpdate(id);
        oldTask = taskListMapper.updateEntity(oldTask, taskDTO);
        taskListRepository.save(oldTask);
        TaskDTO result = taskListMapper.toDTO(oldTask);
        return result;
    }

    @Override
    @Transactional
    public void deleteTaskList(Long[] ids) {
        Arrays.stream(ids).forEach(id -> taskListRepository.deleteById(id));
    }

    @Override
    @Transactional
    public void deleteAllTaskList() {
            taskListRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskListDTO getTaskListById(Long id) {
        TaskEntity taskEntity = taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id"));
        TaskListDTO taskListDTO = taskListMapper.convertDTO(taskEntity);
        taskListDTO.setTaskDTO(setTotalTaskDetailItemsByTaskId(taskEntity, id));
        return taskListDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskOverViewDTO getAllTaskList() {
        TaskOverViewDTO taskOverViewDTO = new TaskOverViewDTO();
        taskOverViewDTO.setTotalTaskItem(taskDetailRepository.count());
        List<TaskEntity> taskEntity = taskListRepository.findAll();
        List<TaskListDTO> taskListDTO = taskListMapper.convertDTOs(taskEntity);
        for (TaskListDTO taskListDTO1 : taskListDTO) {
            TaskEntity taskEntity1 = taskListRepository.findById(taskListDTO1.getTaskDTO().getId()).orElseThrow();
            taskListDTO1.setTaskDTO(setTotalTaskDetailItemsByTaskId(taskEntity1, taskEntity1.getId()));
        }
        taskOverViewDTO.setTaskListDTOS(taskListDTO);
        return taskOverViewDTO;
    }

    public TaskDTO setTotalTaskDetailItemsByTaskId(TaskEntity taskEntity, Long id) {
        TaskDTO taskDTO = taskListMapper.toDTO(taskEntity);
        taskDTO.setTotalTaskItemsByTaskId(taskDetailRepository.countByTasklist_Id(id));
        return taskDTO;
    }


}
