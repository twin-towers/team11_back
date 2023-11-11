package com.example.hackathon11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "canvas")
public class Canvas {
    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "background_image")
    private String backgroundImage;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "padding")
    private int padding;
}
