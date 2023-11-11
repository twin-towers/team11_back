package com.example.hackathon11.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "simple_icons")
@Data
public class SimpleIcon extends GeneralShape {
    @Column(name = "color")
    private String color;

    @Id
    private String id;

    @Column(name="size")
    private int size;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}