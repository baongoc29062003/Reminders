package com.transformtech.reminders.service.impl;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.entity.TaskDetailEntity;
import com.transformtech.reminders.exception.ResourceNotFoundException;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.filter.TaskDetailSpecification;
import com.transformtech.reminders.mapper.TaskDetailMapper;
import com.transformtech.reminders.repositoty.TaskDetailRepository;
import com.transformtech.reminders.service.ITaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskDetailService implements ITaskDetailService {
    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Autowired
    private TaskDetailMapper taskDetailMapper;


    @Override
    public TaskDetailDTO saveTaskDetail(TaskDetailDTO taskDetailDTO) {
        TaskDetailEntity taskDetailEntity;
        if (taskDetailDTO.getId() != null) {
            TaskDetailEntity oldTaskDetail = taskDetailRepository.findById(taskDetailDTO.getId()).orElseThrow();
            taskDetailEntity = taskDetailMapper.updateToEntity(taskDetailDTO, oldTaskDetail);
        } else {
            taskDetailEntity = taskDetailMapper.toEntity(taskDetailDTO);
        }
        taskDetailRepository.save(taskDetailEntity);
        TaskDetailDTO result = taskDetailMapper.toDTO(taskDetailEntity);
        return result;
    }

    @Override
    public void deleteTaskDetail(Long[] ids) {
        Arrays.stream(ids).forEach(id -> taskDetailRepository.deleteById(id));
    }

    @Override
    public void deleteAllTaskDetail() {
        taskDetailRepository.deleteAll();
    }

    @Override
    public TaskDetailDTO findById(Long id) {

        TaskDetailEntity taskDetailEntity = taskDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id"));
        TaskDetailDTO result = taskDetailMapper.toDTO(taskDetailEntity);
        return result;

    }

    @Override
    public List<TaskDetailDTO> findByExcutionDate() {
        LocalDate today = LocalDate.now();
        List<TaskDetailEntity> taskDetailEntities = taskDetailRepository.findByExecutionDate(today);
        List<TaskDetailDTO> taskDetailDTO = taskDetailMapper.toDTOs(taskDetailEntities);
        return taskDetailDTO;
    }

    @Override
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
