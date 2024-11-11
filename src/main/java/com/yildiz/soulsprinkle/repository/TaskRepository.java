package com.yildiz.soulsprinkle.repository;

import com.yildiz.soulsprinkle.model.Task;
import com.yildiz.soulsprinkle.model.preferences.TaskStatusPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task>findByCreatorId(Long creatorId);

    List<Task>findByTaskStatusPreference(TaskStatusPreference taskStatusPreference);

    List<Task>findByDueDateBefore(LocalDateTime date);

    List<Task>findByTitleContaining(String title);

    List<Task>findByTaskStatusPreferenceAndAssigneesId(TaskStatusPreference taskStatusPreference, Long assigneeId);
}
