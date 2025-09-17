package com.SpringProjects.tasks.mappers.impl;

import com.SpringProjects.tasks.domain.dto.TaskListDTO;
import com.SpringProjects.tasks.domain.entities.Task;
import com.SpringProjects.tasks.domain.entities.TaskList;
import com.SpringProjects.tasks.domain.entities.TaskStatus;
import com.SpringProjects.tasks.mappers.TaskListMapper;
import com.SpringProjects.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

private final TaskMapper taskMapper ;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskList fromDto(TaskListDTO taskListDTO) {
        return new TaskList(

               taskListDTO.id(),
               taskListDTO.title(),
               taskListDTO.description(),
                Optional.ofNullable(taskListDTO.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null)
                ,null
                ,null
        );

    }
    @Override
    public TaskListDTO toDto(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0)
        );
    }

    private Double calculateTaskListProgress (List<Task> tasks) {
        if (null == tasks) {
            return null;
        }
       Long closedTaskCount =  tasks.stream().filter(task -> TaskStatus.Closed == task.getStatus() ).count();

        return (double) closedTaskCount / tasks.size();
    }
}
