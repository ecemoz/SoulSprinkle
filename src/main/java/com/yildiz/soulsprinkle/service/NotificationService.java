package com.yildiz.soulsprinkle.service;

import com.yildiz.soulsprinkle.model.Notification;
import com.yildiz.soulsprinkle.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    private NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Optional<Notification>getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public List<Notification> getNotificationsByTaskId(Long taskId) {
        return notificationRepository.findByTaskId(taskId);
    }

    public  Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotificationById(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        }else {
            throw new RuntimeException("Notification not found");
        }
    }
}