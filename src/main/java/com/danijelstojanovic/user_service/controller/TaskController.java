package com.danijelstojanovic.user_service.controller;


import com.danijelstojanovic.user_service.model.Task;
import com.danijelstojanovic.user_service.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/user/{userId}")
    public List<Task> getTaskByUserId(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTasks(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{taskId}/share/{userId}")
    public ResponseEntity<?> shareTask(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.shareTaskWithUser(taskId, userId);
        return ResponseEntity.ok().body("Task shared!");
    }

    @GetMapping("/shared/{userId}")
    public ResponseEntity<List<Task>> getSharedTasks(@PathVariable Long userId) {
        try {
            List<Task> sharedTasks = taskService.getTasksSharedWithUser(userId);
            return ResponseEntity.ok(sharedTasks);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
