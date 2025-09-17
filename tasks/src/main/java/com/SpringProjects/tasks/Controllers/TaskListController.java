package com.SpringProjects.tasks.Controllers;


import com.SpringProjects.tasks.Services.TaskListService;
import com.SpringProjects.tasks.domain.dto.TaskListDTO;
import com.SpringProjects.tasks.domain.entities.TaskList;
import com.SpringProjects.tasks.mappers.TaskListMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {


    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDTO> listTaskLists (){
      return  taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();

    }

    @PostMapping
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTaskLits = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDTO)
        );
        return taskListMapper.toDto(createdTaskLits);
    }

    @GetMapping (path = "/{task_list_id}")
    public Optional<TaskListDTO> getTaskList (@PathVariable ("task_list_id")UUID taskListId) {
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping (path = "/{task_list_id}")
    public TaskListDTO updatedTaskList (@PathVariable ("task_list_id") UUID taskListId , @RequestBody TaskListDTO taskListDTO ) {
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDTO)
        );
        return  taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList (@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
    }
}
