package com.SpringProjects.tasks.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "id", updatable = false , nullable = false)
    private UUID id ;


    @Column(nullable = false)
    private String title ;



    private String description;

    @Column(name = "due_Date")
    private LocalDateTime dueDate ;


    @Column(nullable = false)
    private TaskStatus Status ;

    @Column(nullable = false)
    private TaskPriority priority;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "task_list_id")
    private TaskList taskList ;

    @Column(nullable = false)
    private LocalDateTime created ;

    @Column(nullable = false)
    private LocalDateTime updated ;

}
