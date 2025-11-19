package com.task_mini.task_mini.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    Page<Task> findByUserDuenoId(Long userId, Pageable pageable);
    Optional<Task> findByIdAndUserDuenoId(Long id, Long userId);
}
