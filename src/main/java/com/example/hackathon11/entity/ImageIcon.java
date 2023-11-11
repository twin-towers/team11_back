package com.example.hackathon11.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="image_icons")
@Data
@RequiredArgsConstructor
public class ImageIcon extends GeneralShape {

    @Id
    private String id;

    @Column(name="size")
    private int size;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}