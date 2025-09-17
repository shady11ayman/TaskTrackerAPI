package com.SpringProjects.tasks.Services.impl;

import com.SpringProjects.tasks.Services.TaskService;
import com.SpringProjects.tasks.domain.entities.Task;
import com.SpringProjects.tasks.domain.entities.TaskList;
import com.SpringProjects.tasks.domain.entities.TaskPriority;
import com.SpringProjects.tasks.domain.entities.TaskStatus;
import com.SpringProjects.tasks.repositories.TaskListRepository;
import com.SpringProjects.tasks.repositories.TaskReporitory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskReporitory taskReporitory;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskReporitory taskReporitory, TaskListRepository taskListRepository) {
        this.taskReporitory = taskReporitory;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listAllTasks(UUID TaskListId) {
        return taskReporitory.findByTaskId(TaskListId);
    }

    @Override
    public Task createTask(UUID TaskId, Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("task Already has an id");
        }
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("task must Have an Title");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.Medium);
        TaskStatus taskStatus = TaskStatus.Open;

        TaskList taskList = taskListRepository.findById(TaskId).orElseThrow(()-> new IllegalArgumentException("Invalid TaskList Id "));

        LocalDateTime now = LocalDateTime.now();
        Task TaskToSave = new Task (
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now

        );
        return taskReporitory.save(TaskToSave);

    }

    @Override
    public Optional<Task> getTasks(UUID taskListId, UUID taskId) {
        return taskReporitory.findByTaskListIdAndId(taskListId , taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if (task.getId()== null) {
            throw new IllegalArgumentException("Task Must Have an Id");
        }
        if (!Objects.equals(taskId , task.getId())) {
            throw new IllegalArgumentException("Task Id Don't match");

        }

        if (task.getPriority()== null) {
            throw new IllegalArgumentException("Task must have valid Priority");
        }
        if (task.getStatus()== null) {
            throw new IllegalArgumentException("Task must have valid Status");
        }
      Task existingTask =   taskReporitory.findByTaskListIdAndId(taskListId,taskId).orElseThrow(()-> new IllegalArgumentException("Task Not Found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setUpdated(LocalDateTime.now());

        return taskReporitory.save( existingTask);

    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
  taskReporitory.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
