package com.yildiz.soulsprinkle.service;

import com.yildiz.soulsprinkle.model.Task;
import com.yildiz.soulsprinkle.model.preferences.TaskStatusPreference;
import com.yildiz.soulsprinkle.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id){
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());

                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByCreatorId(Long creatorId){
        return taskRepository.findByCreatorId(creatorId);
    }

    public List<Task>getTasksByStatus(TaskStatusPreference statusPreference){
        return taskRepository.findByTaskStatusPreference(statusPreference);
    }

    public List <Task>getTasksDueBefore(LocalDateTime dateTime){
        return taskRepository.findByDueDateBefore(dateTime);
    }

    public List<Task>searchTasksByTitle(String title){
        return taskRepository.findByTitleContaining(title);
    }

    public List<Task>getTasksByStatusAndAssignee(TaskStatusPreference statusPreference, Long assigneeId){
        return taskRepository.findByTaskStatusPreferenceAndAssigneesId(statusPreference, assigneeId);
    }
}
