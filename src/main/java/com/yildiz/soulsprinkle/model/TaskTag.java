package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="task_tags")

public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn( name ="task_id",nullable = false)
    private Task task;

    private String tagName;

    public TaskTag( String tagName, Task task) {
        this.tagName = tagName;
        this.task = task;
    }

    public TaskTag() {
    }
}
