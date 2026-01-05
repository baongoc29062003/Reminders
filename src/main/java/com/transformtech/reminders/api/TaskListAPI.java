package com.transformtech.reminders.api;

import com.transformtech.reminders.dto.TaskDTO;
import com.transformtech.reminders.dto.TaskListDTO;
import com.transformtech.reminders.dto.TaskOverViewDTO;
import com.transformtech.reminders.service.ITaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskListAPI {
    @Autowired
    private ITaskListService taskListService;

    @GetMapping("/api/tasklist")
    public List<TaskDTO> findListTask ()
    {
        return taskListService.findListTask();
    }

    @GetMapping("/api/tasklist/{id}")
    public TaskListDTO findTaskById (@PathVariable Long id)
    {
        return taskListService.getTaskListById(id);
    }

    @GetMapping("/api/tasklistall")
    public TaskOverViewDTO findAll ()
    {
        return taskListService.getAllTaskList();
    }

    @PostMapping("/api/tasklist")
    public TaskDTO saveTaskList (@RequestBody TaskDTO taskListDTO)
    {
        return taskListService.saveTaskList(taskListDTO);
    }

    @PutMapping("/api/tasklist")
    public TaskDTO updateTaskList (@RequestBody TaskDTO taskListDTO)
    {
        return taskListService.updateTaskList(taskListDTO);
    }

    @DeleteMapping("/api/tasklist")
    void deleteTasklist (@RequestBody Long[] ids)
    {
        taskListService.deleteTaskList(ids);
    }

    @DeleteMapping("/api/tasklist/all")
    void deleteALLTasklist ()
    {
        taskListService.deleteAllTaskList();
    }
}
