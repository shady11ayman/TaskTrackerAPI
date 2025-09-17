package com.SpringProjects.tasks.mappers;

import com.SpringProjects.tasks.domain.dto.TaskDTO;
import com.SpringProjects.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto (TaskDTO taskDTO ) ;

    TaskDTO  toDto (Task task ) ;
}
