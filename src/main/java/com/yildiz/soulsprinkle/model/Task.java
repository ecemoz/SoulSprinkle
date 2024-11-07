package com.yildiz.soulsprinkle.model;

import com.yildiz.soulsprinkle.model.preferences.TaskStatusPreference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Boolean isMandatory = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatusPreference taskStatusPreference = TaskStatusPreference.PENDING;

    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn (name = "creator_id" , nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "task_assignees",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> assignees;

    @OneToMany(mappedBy = "relatedTasks" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PositiveAction>positiveActions;

    public void updateStatus(TaskStatusPreference newStatusPreference) {
        switch (newStatusPreference) {
                case INPROGRESS :
                if (this.taskStatusPreference == TaskStatusPreference.PENDING) {
                    this.taskStatusPreference = TaskStatusPreference.INPROGRESS;
                }
                break;
                case COMPLETED :
                if (this.taskStatusPreference == TaskStatusPreference.INPROGRESS) {
                    this.taskStatusPreference = TaskStatusPreference.COMPLETED;
                }
                break;
                case CANCELLED:
                this.taskStatusPreference = TaskStatusPreference.CANCELLED;
                break;
            default:
                throw new IllegalArgumentException("Invalid status preference:" + newStatusPreference);
        }
    }
}
