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

    public Notification updateNotification( Long id , Notification updatedNotification) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setMessage(updatedNotification.getMessage());
                    notification.setReadStatus(updatedNotification.getReadStatus());
                    return notificationRepository.save(notification);
                })  .orElseThrow(()-> new RuntimeException("Notification not found."));
    }

    public void deleteNotificationById(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        }else {
            throw new RuntimeException("Notification not found");
        }
    }
}