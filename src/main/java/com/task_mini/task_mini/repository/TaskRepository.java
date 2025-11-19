package com.task_mini.task_mini.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    Optional<Task> findByIdAndUserDuenoUsername(Long id, String username);
    List<Task> findAllByUserDuenoUsername(String username);

}
