package com.yildiz.soulsprinkle.service;

import com.yildiz.soulsprinkle.model.PositiveAction;
import com.yildiz.soulsprinkle.repository.PositiveActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PositiveActionService {

    private final PositiveActionRepository positiveActionRepository;

    @Autowired
    public PositiveActionService(PositiveActionRepository positiveActionRepository) {
        this.positiveActionRepository = positiveActionRepository;
    }

    public Optional<PositiveAction>getPositiveActionByTaskId(Long taskId) {
        return positiveActionRepository.findByReleatedTasksId(taskId);
    }

    public PositiveAction savePositiveAction(PositiveAction positiveAction) {
        return positiveActionRepository.save(positiveAction);
    }

    public void deletePositiveAction (Long taskId) {
        if (positiveActionRepository.existsById(taskId)) {
            positiveActionRepository.deleteById(taskId);
        } else {
            throw new RuntimeException("Task doesn't exist");
        }
    }
}