package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@Entity
@Table(name ="tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    @OneToMany(mappedBy = "task")
    private Set<TaskTag> tasktags;

    @ManyToOne
    @JoinColumn(name ="category_id")
    private Category category;

    public void completeTask() {
        this.completed = true;
        if (assignedUser != null) {
            Notification notification = new Notification("Task completed'", "'The task" + title + "has been completed by" + user.getUsername());
            assignedUser.getNotifications().add(notification);
            Map<User, Notification> notificationMap = new HashMap<>();
            notificationMap.put(assignedUser, notification);

            for (User recipient : notificationMap.keySet()) {
                recipient.getNotifications().add(notificationMap.get(recipient));
                notification.getRecipients().add(recipient);
            }
        } else {
            System.out.println("No user assigned to this task");
            return;
        }
    }
}
