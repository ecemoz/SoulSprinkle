package com.yildiz.soulsprinkle.controller;

import com.yildiz.soulsprinkle.model.Task;
import com.yildiz.soulsprinkle.model.preferences.TaskStatusPreference;
import com.yildiz.soulsprinkle.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task>getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(id, updatedTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity <List<Task>>getTasksByCreatorId(@PathVariable Long creatorId) {
        List<Task> tasks = taskService.getTasksByCreatorId(creatorId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam TaskStatusPreference taskStatusPreference) {
        List<Task> tasks = taskService.getTasksByStatus(taskStatusPreference);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/due-before")
    public ResponseEntity<List<Task>> getTasksByDueBefore(@RequestParam LocalDateTime dateTime) {
        List<Task> tasks = taskService.getTasksDueBefore(dateTime);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByTitle(@RequestParam String title) {
        List<Task>tasks = taskService.searchTasksByTitle(title);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/status-and-assignee")
    public ResponseEntity<List<Task>> getTasksByStatusAndAssignee
            (@RequestParam TaskStatusPreference taskStatusPreference,
             @RequestParam Long assigneeId) {
        List<Task>tasks = taskService.getTasksByStatusAndAssignee(taskStatusPreference, assigneeId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}