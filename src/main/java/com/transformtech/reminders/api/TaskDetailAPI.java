package com.transformtech.reminders.api;

import com.transformtech.reminders.dto.TaskDetailDTO;
import com.transformtech.reminders.enums.Priority;
import com.transformtech.reminders.enums.Status;
import com.transformtech.reminders.filter.TaskDetailFilter;
import com.transformtech.reminders.service.ITaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TaskDetailDTO> getTaskDetailFilter(@RequestParam(required = false) String q,
                                                   @RequestParam(required = false) Status status,
                                                   @RequestParam(required = false) Priority priority) {
        TaskDetailFilter filter = new TaskDetailFilter(q, status, priority);

        return taskDetailService.search(filter);
    }

}
