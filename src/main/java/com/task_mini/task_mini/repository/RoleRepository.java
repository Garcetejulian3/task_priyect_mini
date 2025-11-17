package com.task_mini.task_mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{

}
