package com.task_mini.task_mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.PermisionEntity;

@Repository
public interface PermisionRepository extends JpaRepository<PermisionEntity,Long>{

}
