package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name = "user_preferences")
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private boolean receiveNotifications;

    private String theme;

    private String language;

    private boolean enableReminders;
}
