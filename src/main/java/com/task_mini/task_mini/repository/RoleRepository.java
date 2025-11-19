package com.task_mini.task_mini.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.RoleEntity;
import com.task_mini.task_mini.models.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
