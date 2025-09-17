package com.SpringProjects.tasks.Controllers;


import com.SpringProjects.tasks.Services.TaskService;
import com.SpringProjects.tasks.domain.dto.TaskDTO;
import com.SpringProjects.tasks.domain.entities.Task;
import com.SpringProjects.tasks.mappers.TaskMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {

private final TaskService taskService;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDTO> listAllTasks(@PathVariable("task_list_id") UUID taskId) {
        return taskService.listAllTasks(taskId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDTO createTask (@PathVariable("task_list_id") UUID taskId , @RequestBody TaskDTO taskDTO) {

    Task createdTask =  taskService.createTask(taskId , taskMapper.fromDto(taskDTO));

    return taskMapper.toDto(createdTask);
    }

    @GetMapping (path = "/{task_Id}")
    public Optional<TaskDTO> getTask (
            @PathVariable("task_list_id") UUID taskListId
            ,  @PathVariable("task_id") UUID taskId
    ) {
        return taskService.getTasks(taskListId,taskId).map(taskMapper::toDto);
    }


    @PutMapping (path = "/{task_Id}")
    public TaskDTO updatedTask (   @PathVariable("task_list_id") UUID taskListId
            ,  @PathVariable("task_id") UUID taskId, @RequestBody TaskDTO taskDTO)  {

       Task UpdatedTask = taskService.updateTask(taskListId,taskId,taskMapper.fromDto(taskDTO));

       return taskMapper.toDto(UpdatedTask);

    }

    @DeleteMapping (path = "/{task_Id}")
    public void deleteTask (@PathVariable("task_list_id") UUID taskListId
            ,  @PathVariable("task_id") UUID taskId){
        taskService.deleteTask(taskListId,taskId);
    }

}
