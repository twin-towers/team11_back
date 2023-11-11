package com.example.hackathon11.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "images")
@Data
public class Image extends GeneralShape {
    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "fit")
    @Enumerated(EnumType.STRING)
    private Fit fit;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "src")
    private String src;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}