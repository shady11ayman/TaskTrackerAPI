package com.SpringProjects.tasks.mappers;

import com.SpringProjects.tasks.domain.dto.TaskListDTO;
import com.SpringProjects.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto (TaskListDTO taskListDTO);
    TaskListDTO toDto (TaskList taskList);
}
