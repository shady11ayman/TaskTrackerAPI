package com.SpringProjects.tasks.repositories;

import com.SpringProjects.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskReporitory extends JpaRepository<Task, UUID> {

    List<Task> findByTaskId (UUID taskListId);

    Optional<Task> findByTaskListIdAndId (UUID taskListId , UUID id );

    void deleteByTaskListIdAndId (UUID taskListId , UUID id );
}
