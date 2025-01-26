package com.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hair")
public class Hair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private String type;
}
