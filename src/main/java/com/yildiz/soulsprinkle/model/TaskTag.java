package com.yildiz.soulsprinkle.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="task_tags")

public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}





