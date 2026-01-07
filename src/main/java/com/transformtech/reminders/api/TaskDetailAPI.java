package com.transformtech.reminders.api;

import com.transformtech.reminders.dto.PageResp;
import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.service.ITaskDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class TaskDetailAPI {
    private final ITaskDetailService taskDetailService;

    @PostMapping("/api/taskdetail")
    public TaskDetailDTO createTaskDetail(@RequestBody TaskDetailDTO taskDetailDTO) {
        log.info("Creating task detail: {}", taskDetailDTO);
        return taskDetailService.saveTaskDetail(taskDetailDTO);
    }

    @PatchMapping("api/taskdetail/{id}")
    public TaskDetailDTO updateTaskDetail(@PathVariable Long id,
                                          @RequestBody TaskDetailDTO taskDetailDTO) {
        log.info("Updating task detail: {}", taskDetailDTO);
        return taskDetailService.updateTaskDetail(taskDetailDTO,id);
    }

    @DeleteMapping("/api/taskdetail")
    public void deleteTaskDetail(@RequestParam(value = "ids") Long[] ids) {
        log.info("Deleting task detail: {}", ids);
        taskDetailService.deleteTaskDetail(ids);
    }

    @DeleteMapping("/api/taskdetail/all")
    public void deleteAllTaskDetail() {
        taskDetailService.deleteAllTaskDetail();
    }

    @GetMapping("/api/taskdetail/{id}")
    public TaskDetailDTO getTaskDetailById(@PathVariable Long id) {
        log.info("Getting task detail: {}", id);
        return taskDetailService.findById(id);
    }

    @GetMapping("/api/taskdetail/today")
    public List<TaskDetailDTO> getTaskDetailToday() {
        log.info("Getting task detail today");
        return taskDetailService.findByExcutionDate();
    }

    @GetMapping("/api/taskdetail/filter")
    public PageResp<TaskDetailDTO> getTaskDetailFilter(@RequestParam(required = false) String q,
                                                       @RequestParam(required = false) Status status,
                                                       @RequestParam(required = false) Priority priority,
                                                       @RequestParam(required = false)  int limit,
                                                       @RequestParam(required = false)  int offset) {
        log.info("Getting task detail filter: {}", q);
        Pageable pageable =  PageRequest.of(offset, limit);
        TaskDetailFilter filter = new TaskDetailFilter(q, status, priority);
        return taskDetailService.search(filter,pageable );
    }

}
