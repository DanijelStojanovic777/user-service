package com.danijelstojanovic.user_service.service;

import com.danijelstojanovic.user_service.model.Task;
import com.danijelstojanovic.user_service.model.User;
import com.danijelstojanovic.user_service.repository.TaskRepository;
import com.danijelstojanovic.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService (TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }


    public void shareTaskWithUser(Long taskId, Long userId) {

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));

        user.getSharedTasks().add(task);
        userRepository.save(user);
    }

    public List<Task> getTasksSharedWithUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getSharedTasks();
    }

}
