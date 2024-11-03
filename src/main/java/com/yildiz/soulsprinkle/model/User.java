package com.yildiz.soulsprinkle.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name= "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "assignedUser")
    private Set<Task> assignedTasks;

    @ManyToMany
    @JoinTable(
            name = "user_notifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private Set<Notification> notifications;
}

