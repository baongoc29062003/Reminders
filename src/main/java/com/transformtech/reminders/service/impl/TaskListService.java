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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskListService implements ITaskListService {
    private final TaskMapper taskListMapper;

    private final TaskListRepository taskListRepository;

    private final TaskDetailRepository taskDetailRepository;

    private final TaskValidate taskValidate;


    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findListTask() {
        log.debug("Finding list of tasks");
        List<TaskEntity> taskListEntity = taskListRepository.findAllByIsActiveTrue();
        List<TaskDTO> taskListDTO = taskListMapper.toDTOs(taskListEntity);
        for (TaskDTO taskDTO : taskListDTO) {
            taskDTO.setTotalTaskItemsByTaskId(taskDetailRepository.countByTasklist_Id(taskDTO.getId()));
        }

        return taskListDTO;
    }


    @Override
    @Transactional
    public TaskDTO saveTaskList(TaskDTO taskListDTO) {
        log.debug("Saving list of tasks {}", taskListDTO);
        TaskEntity taskListEntity = taskListMapper.toEntity(taskListDTO);
        taskListEntity = taskListRepository.save(taskListEntity);
        return taskListMapper.toDTO(taskListEntity);
    }

    @Override
    @Transactional
    public TaskDTO updateTaskList(TaskDTO taskDTO, Long id) {
        log.debug("Updating list of tasks{}",taskDTO);
        TaskEntity oldTask = taskValidate.validateTaskUpdate(id);
        oldTask = taskListMapper.updateEntity(oldTask, taskDTO);
        taskListRepository.save(oldTask);
        return taskListMapper.toDTO(oldTask);
    }

    @Override
    @Transactional
    public void deleteTaskList(Long[] ids) {
        log.debug("Deleting list of tasks");
        List<TaskEntity> taskEntities = taskListRepository.findAllById(Arrays.asList(ids));
        taskEntities
                .stream()
                .forEach(taskEntity ->
                        {
                            taskEntity.setActive(false);
                            taskListRepository.save(taskEntity);
                        }
                );
    }

    @Override
    @Transactional
    public void deleteAllTaskList() {
        List<TaskEntity> taskEntities = taskListRepository.findAllByIsActiveTrue();
        taskEntities
                .stream()
                .forEach(taskEntity ->
                {
                    taskEntity.setActive(false);
                    taskListRepository.save(taskEntity);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public TaskListDTO getTaskListById(Long id) {
        log.debug("Retrieving list of tasks by id {}", id);
        TaskEntity taskEntity = taskListRepository.findAllByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy id"));
        if (taskEntity.isActive()) {
            TaskListDTO taskListDTO = taskListMapper.convertDTO(taskEntity);
            taskListDTO.setTaskDTO(setTotalTaskDetailItemsByTaskId(taskEntity, id));
            return taskListDTO;
        }
        else return null;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskOverViewDTO getAllTaskList() {
        log.debug("Retrieving list of tasks");
        TaskOverViewDTO taskOverViewDTO = new TaskOverViewDTO();
        taskOverViewDTO.setTotalTaskItem(taskDetailRepository.count());
        List<TaskEntity> taskEntity = taskListRepository.findAllByIsActiveTrue();
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
