package com.danijelstojanovic.user_service.repository;


import com.danijelstojanovic.user_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

}
