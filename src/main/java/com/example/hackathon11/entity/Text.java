package com.example.hackathon11.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="texts")
@Data
@RequiredArgsConstructor
public class Text extends GeneralShape {

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "decoration")
    @Enumerated(EnumType.STRING)
    private Decoration decoration;

    @Column(name = "href")
    private String hRef;

    @Column(name = "is_italic")
    private boolean isItalic;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private int size;

    @Column(name = "text")
    private String text;

    @Column(name = "weight")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}