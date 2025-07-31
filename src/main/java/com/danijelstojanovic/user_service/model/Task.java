package com.danijelstojanovic.user_service.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "useri_id")
    @JsonBackReference
    private User user;


    @ManyToMany(mappedBy = "sharedTasks")
    @JsonIgnore
    private List<User> sharedWithUsers = new ArrayList<>();


    public Task() {

    }

    public Task(Long id, String title, String description, LocalDate dueDate, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getSharedWithUsers() {
        return sharedWithUsers;
    }

    public void setSharedWithUsers(List<User> sharedWithUsers) {
        this.sharedWithUsers = sharedWithUsers;
    }
}
