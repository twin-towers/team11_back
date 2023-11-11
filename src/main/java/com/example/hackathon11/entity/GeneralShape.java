package com.example.hackathon11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class GeneralShape {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @Column(name="rotate")
    private int rotate;

    @Column(name="z_index")
    private int zIndex;
}
