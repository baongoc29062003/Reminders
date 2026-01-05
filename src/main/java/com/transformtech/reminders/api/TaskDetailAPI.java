package com.transformtech.reminders.api;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.service.ITaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskDetailAPI {
    @Autowired
    private ITaskDetailService taskDetailService;

    @PostMapping("/api/taskdetail")
    public TaskDetailDTO createTaskDetail(@RequestBody TaskDetailDTO taskDetailDTO) {
        return taskDetailService.saveTaskDetail(taskDetailDTO);
    }
//api put, delete cho truyền id vào qua url
    @PutMapping("api/taskdetail")
    public TaskDetailDTO updateTaskDetail(@RequestBody TaskDetailDTO taskDetailDTO) {
        return taskDetailService.saveTaskDetail(taskDetailDTO);
    }

    @DeleteMapping("/api/taskdetail")
    public void deleteTaskDetail(@RequestBody Long[] ids) {
        taskDetailService.deleteTaskDetail(ids);
    }

    @DeleteMapping("/api/taskdetail/all")
    public void deleteAllTaskDetail() {
        taskDetailService.deleteAllTaskDetail();
    }

    @GetMapping("/api/taskdetail/{id}")
    public TaskDetailDTO getTaskDetailById(@PathVariable Long id) {
        return taskDetailService.findById(id);
    }

    @GetMapping("/api/taskdetail/today")
    public List<TaskDetailDTO> getTaskDetailToday() {
        return taskDetailService.findByExcutionDate();
    }

    @GetMapping("/api/taskdetail/filter")
    public Page<TaskDetailDTO> getTaskDetailFilter(@RequestParam(required = false) String q,
                                                   @RequestParam(required = false) Status status,
                                                   @RequestParam(required = false) Priority priority,
                                                   @RequestParam(required = false)  int limit,
                                                   @RequestParam(required = false)  int offset) {
        Pageable pageable =  PageRequest.of(offset, limit);
        TaskDetailFilter filter = new TaskDetailFilter(q, status, priority);
        return taskDetailService.search(filter,pageable );
    }

}
