package com.transformtech.reminders.mapper;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.entity.TaskDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskDetailMapper {


    @Mapping(target = "tasklistId" , source = "tasklist.id")
    TaskDetailDTO toDTO (TaskDetailEntity entity);


    @Mapping(target = "tasklistId" , source = "tasklist.id")
    List<TaskDetailDTO> toDTOs (List<TaskDetailEntity> entity);


    @Mapping(target = "tasklist.id" , source = "tasklistId")
    TaskDetailEntity toEntity (TaskDetailDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate" , ignore = true)
    @Mapping(target = "modifiedDate" , ignore = true)
    TaskDetailEntity updateToEntity (TaskDetailDTO dto,@MappingTarget TaskDetailEntity entity);
}
