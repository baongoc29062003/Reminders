package com.transformtech.reminders.mapper;

import com.transformtech.reminders.dto.TaskDTO;
import com.transformtech.reminders.dto.TaskListDTO;
import com.transformtech.reminders.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring", uses = TaskDetailMapper.class)
public interface TaskMapper {

    List<TaskDTO> toDTOs(List<TaskEntity> taskListEntity);

    TaskDTO toDTO(TaskEntity taskListEntity);


    @Mapping(target = "taskDTO" , source =".")
    @Mapping(target = "taskDetails" , source ="taskDetails")
    List<TaskListDTO> convertDTOs(List<TaskEntity> taskListEntity);

    @Mapping(target = "taskDTO" , source =".")
    @Mapping(target = "taskDetails" , source ="taskDetails")
    TaskListDTO convertDTO(TaskEntity taskListEntity);

    TaskEntity toEntity(TaskListDTO taskListDTO);

    TaskEntity toEntity(TaskDTO taskListDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate" , ignore = true)
    @Mapping(target = "modifiedDate" , ignore = true)
    TaskEntity updateEntity(@MappingTarget TaskEntity taskListEntity,TaskListDTO taskListDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate" , ignore = true)
    @Mapping(target = "modifiedDate" , ignore = true)
    TaskEntity updateEntity(@MappingTarget TaskEntity taskListEntity,TaskDTO taskListDTO);
}
