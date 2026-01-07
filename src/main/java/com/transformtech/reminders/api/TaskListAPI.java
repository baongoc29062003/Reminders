package com.transformtech.reminders.api;

import com.transformtech.reminders.dto.TaskDTO;
import com.transformtech.reminders.dto.TaskListDTO;
import com.transformtech.reminders.dto.TaskOverViewDTO;
import com.transformtech.reminders.service.ITaskListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TaskListAPI {
    @Autowired
    private ITaskListService taskListService;

    @GetMapping("/api/tasklist")
    public List<TaskDTO> findListTask() {
        log.info("Finding list of tasks");
        return taskListService.findListTask();
    }

    @GetMapping("/api/tasklist/{id}")
    public TaskListDTO findTaskById(@PathVariable Long id) {
        log.info("Finding list of tasks by id: {}", id);
        return taskListService.getTaskListById(id);
    }

    @GetMapping("/api/tasklistall")
    public TaskOverViewDTO findAll() {
        log.info("Finding list of tasks all");
        return taskListService.getAllTaskList();
    }

    @PostMapping("/api/tasklist")
    public TaskDTO saveTaskList(@RequestBody TaskDTO taskListDTO) {
        log.info("Saving task list: {}", taskListDTO);
        return taskListService.saveTaskList(taskListDTO);
    }

    @PutMapping("/api/tasklist/{id}")
    public TaskDTO updateTaskList(@PathVariable Long id,
                                  @RequestBody TaskDTO taskListDTO) {
        log.info("Updating task list: {}", taskListDTO);
        return taskListService.updateTaskList(taskListDTO,id);
    }

    @DeleteMapping("/api/tasklist")
    void deleteTasklist(@RequestParam(value = "ids") Long[] ids) {
        log.info("Deleting task list: {}", ids);
        taskListService.deleteTaskList(ids);
    }

    @DeleteMapping("/api/tasklist/all")
    void deleteALLTasklist() {
        log.info("Deleting all task list");
        taskListService.deleteAllTaskList();
    }
}
