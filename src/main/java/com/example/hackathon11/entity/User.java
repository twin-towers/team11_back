package com.example.hackathon11.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;        //EMAIL

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")      //USERNAME
    private String nickname;

//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
//    private List<Project> projects;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<MemoryRecord> memoryRecords;

}
