package com.SpringProjects.tasks.domain.dto;

import com.SpringProjects.tasks.domain.entities.TaskPriority;
import com.SpringProjects.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(

        UUID id ,
        String title ,
        String description,
        LocalDateTime dueDate ,
        TaskPriority priority,
        TaskStatus status
) {
}
