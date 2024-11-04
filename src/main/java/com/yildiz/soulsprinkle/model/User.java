package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String pictureUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_settings_id", referencedColumnName = "id")
    private ProfileSettings profileSettings;
}

//      - createdTasks: One-to-Many relationship with Task (creator)
//      - assignedTasks: Many-to-Many relationship with Task (assignee)
//      - notifications: One-to-Many relationship with Notification