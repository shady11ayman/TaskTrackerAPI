package com.SpringProjects.tasks.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDTO(


         UUID id ,
        String title ,
        String description ,
         List<TaskDTO> tasks ,

         Double progress,

        Integer count
) {
}
