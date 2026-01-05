package com.transformtech.reminders.service.impl;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.exception.ResourceNotFoundException;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.filter.TaskDetailSpecification;
import com.transformtech.reminders.mapper.TaskDetailMapper;
import com.transformtech.reminders.repository.TaskDetailRepository;
import com.transformtech.reminders.service.ITaskDetailService;
import com.transformtech.reminders.validate.TaskDetailValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskDetailService implements ITaskDetailService {
    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Autowired
    private TaskDetailMapper taskDetailMapper;

    @Autowired
    private TaskDetailValidate taskDetailValidate;

    @Override
    @Transactional
    public TaskDetailDTO saveTaskDetail(TaskDetailDTO taskDetailDTO) {
        TaskDetailEntity taskDetailEntity = taskDetailMapper.toEntity(taskDetailDTO);
        taskDetailRepository.save(taskDetailEntity);
        TaskDetailDTO result = taskDetailMapper.toDTO(taskDetailEntity);
        return result;
    }

    @Override
    public TaskDetailDTO updateTaskDetail(TaskDetailDTO taskDetailDTO, Long id) {
        TaskDetailEntity oldTaskDetailEntity = taskDetailValidate.validateTaskDetailUpdate(id);
        oldTaskDetailEntity = taskDetailMapper.updateToEntity(taskDetailDTO, oldTaskDetailEntity);
        TaskDetailDTO result = taskDetailMapper.toDTO(oldTaskDetailEntity);
        return result;
    }


    @Override
    @Transactional
    public void deleteTaskDetail(Long[] ids) {
        Arrays.stream(ids).forEach(id -> taskDetailRepository.deleteById(id));
    }

    @Override
    @Transactional
    public void deleteAllTaskDetail() {
        taskDetailRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDetailDTO findById(Long id) {

        TaskDetailEntity taskDetailEntity = taskDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id"));
        TaskDetailDTO result = taskDetailMapper.toDTO(taskDetailEntity);
        return result;

    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDetailDTO> findByExcutionDate() {
        LocalDate today = LocalDate.now();
        List<TaskDetailEntity> taskDetailEntities = taskDetailRepository.findByExecutionDate(today);
        List<TaskDetailDTO> taskDetailDTO = taskDetailMapper.toDTOs(taskDetailEntities);
        return taskDetailDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskDetailDTO> search(TaskDetailFilter filter, Pageable pageable) {
        Specification<TaskDetailEntity> spec = Specification
                .where(TaskDetailSpecification.titleContains(filter.getQ()))
                .and(TaskDetailSpecification.hasStatus(filter.getStatus()))
                .and(TaskDetailSpecification.hasPriority(filter.getPriority()));
        Page<TaskDetailEntity> taskDetailEntity = taskDetailRepository.findAll(spec, pageable);
        Page<TaskDetailDTO> taskDetailDTO = taskDetailEntity.map(taskDetailMapper::toDTO);
        return taskDetailDTO;
    }
}
