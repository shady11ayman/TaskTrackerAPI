package com.SpringProjects.tasks.Services.impl;

import com.SpringProjects.tasks.Services.TaskListService;
import com.SpringProjects.tasks.domain.entities.TaskList;
import com.SpringProjects.tasks.repositories.TaskListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("TaskList Already Have An ID");

        }

        if (taskList.getTitle() != null || taskList.getTitle().isBlank()) {

            throw new IllegalArgumentException("TaskList title ust be presented");
        }

        LocalDateTime now = LocalDateTime.now();
        return  taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,now

        ));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (taskList.getId()==null) {
            throw new IllegalArgumentException("TaskList must have an id") ;
        }
        if (!Objects.equals(taskList.getId() , taskListId)) {
            throw new IllegalArgumentException("Id must be match");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList Not Found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
