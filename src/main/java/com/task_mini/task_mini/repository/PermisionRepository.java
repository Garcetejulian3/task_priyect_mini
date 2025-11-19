package com.task_mini.task_mini.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.PermisionEntity;

@Repository
public interface PermisionRepository extends JpaRepository<PermisionEntity,Long>{
	Optional<PermisionEntity> findByName(String name);
}
