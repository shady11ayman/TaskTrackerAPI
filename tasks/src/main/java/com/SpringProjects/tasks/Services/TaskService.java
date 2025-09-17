package com.SpringProjects.tasks.Services;

import com.SpringProjects.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> listAllTasks (UUID TaskListId);
    Task createTask (UUID TaskId , Task task);
    Optional<Task> getTasks (UUID taskListId , UUID taskId );

    Task updateTask (UUID taskListId , UUID taskId , Task task);

    void deleteTask (UUID taskListId ,  UUID taskId);
}
