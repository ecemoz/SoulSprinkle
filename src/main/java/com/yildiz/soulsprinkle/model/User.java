package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> createdTasks;

    @ManyToMany(mappedBy = "assignees")
    private List<Task> assignedTasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
}