package com.SpringProjects.tasks.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    @Column (name = "id", updatable = false, nullable = false)
    private UUID id ;


    @Column(nullable = false)
    private String title ;

    private String description ;


    @OneToMany(mappedBy = "taskList" , cascade = { CascadeType.REMOVE , CascadeType.PERSIST})
    private List<Task> tasks ;




    @Column(nullable = false)
    private LocalDateTime created ;

    @Column(nullable = false)
    private LocalDateTime updated ;
}
