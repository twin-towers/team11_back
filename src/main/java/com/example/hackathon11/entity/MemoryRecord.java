package com.example.hackathon11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "memory_records")
public class MemoryRecord {
    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    private MemoryDifficulty difficulty;

    @Column(name = "moves")
    private Integer moves;

    @Column(name = "time")
    private Integer time;           //milliseconds

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
