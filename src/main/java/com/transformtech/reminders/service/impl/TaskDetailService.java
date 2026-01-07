package com.transformtech.reminders.service.impl;

import com.transformtech.reminders.dto.PageResp;
import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.exception.ResourceNotFoundException;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.spec.TaskDetailSpecification;
import com.transformtech.reminders.mapper.TaskDetailMapper;
import com.transformtech.reminders.repository.TaskDetailRepository;
import com.transformtech.reminders.service.ITaskDetailService;
import com.transformtech.reminders.validate.TaskDetailValidate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskDetailService implements ITaskDetailService {
    private final TaskDetailRepository taskDetailRepository;

    private final TaskDetailMapper taskDetailMapper;

    private final TaskDetailValidate taskDetailValidate;

    @Override
    @Transactional
    public TaskDetailDTO saveTaskDetail(TaskDetailDTO taskDetailDTO) {
        log.debug("Saving task detail: {}", taskDetailDTO);
        TaskDetailEntity taskDetailEntity = taskDetailMapper.toEntity(taskDetailDTO);
        taskDetailRepository.save(taskDetailEntity);
        return taskDetailMapper.toDTO(taskDetailEntity);
    }

    @Override
    @Transactional
    public TaskDetailDTO updateTaskDetail(TaskDetailDTO taskDetailDTO, Long id) {
        log.debug("Updating task detail: {}", taskDetailDTO);
        TaskDetailEntity oldTaskDetailEntity = taskDetailValidate.validateTaskDetailUpdate(id);
        oldTaskDetailEntity = taskDetailMapper.updateToEntity(taskDetailDTO, oldTaskDetailEntity);
        taskDetailRepository.save(oldTaskDetailEntity);
        return taskDetailMapper.toDTO(oldTaskDetailEntity);
    }


    @Override
    @Transactional
    public void deleteTaskDetail(Long[] ids) {
        log.debug("Deleting task details: {}", Arrays.asList(ids));
        List<TaskDetailEntity> taskDetailEntities = taskDetailRepository.findAllById(Arrays.asList(ids));
        taskDetailEntities
                .stream()
                .forEach(taskDetailEntity ->
                {
                    taskDetailEntity.setActive(false);
                    taskDetailRepository.save(taskDetailEntity);
                });
    }

    @Override
    @Transactional
    public void deleteAllTaskDetail() {
        log.debug("Deleting all task details");
        List<TaskDetailEntity> taskDetailEntities = taskDetailRepository.findAllByIsActiveTrue();
        taskDetailEntities
                .stream()
                .forEach(taskDetailEntity ->
                {
                    taskDetailEntity.setActive(false);
                    taskDetailRepository.save(taskDetailEntity);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDetailDTO findById(Long id) {
        log.debug("Finding task detail: {}", id);
        TaskDetailEntity taskDetailEntity = taskDetailValidate.validateTaskDetailUpdate(id);
        if (taskDetailEntity.isActive() == true) {
            return taskDetailMapper.toDTO(taskDetailEntity);
        }
        throw new RuntimeException("Task detail not found");
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDetailDTO> findByExcutionDate() {
        log.debug("Finding task details by excution date");
        LocalDate today = LocalDate.now();
        List<TaskDetailEntity> taskDetailEntities = taskDetailRepository.findByExecutionDate(today);
        List<TaskDetailEntity> activeEntity = taskDetailEntities
                .stream()
                .filter(TaskDetailEntity::isActive)
                .toList();
        return taskDetailMapper.toDTOs(activeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResp<TaskDetailDTO> search(TaskDetailFilter filter, Pageable pageable) {
        log.debug("Searching task details for filter: {}", filter);
        Specification<TaskDetailEntity> spec = Specification
                .where(TaskDetailSpecification.isActive())
                .and(TaskDetailSpecification.titleContains(filter.getQ()))
                .and(TaskDetailSpecification.hasStatus(filter.getStatus()))
                .and(TaskDetailSpecification.hasPriority(filter.getPriority()));
        Page<TaskDetailDTO> taskDetailEntity = taskDetailRepository.findAll(spec, pageable).map(taskDetailMapper::toDTO);
        return new PageResp<TaskDetailDTO>(taskDetailEntity);
    }
}
