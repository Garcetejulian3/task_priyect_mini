package com.task_mini.task_mini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_mini.task_mini.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
