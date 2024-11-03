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


