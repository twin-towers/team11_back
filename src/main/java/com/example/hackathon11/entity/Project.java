package com.example.hackathon11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_name_user", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canvas_id", referencedColumnName = "id")
    private Canvas canvas;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "project")
    private List<SimpleIcon> simpleIcons;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "project")
    private List<ImageIcon> imageIcons;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "project")
    private List<Image> images;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "project")
    private List<Text> texts;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @UpdateTimestamp
    @Column(name="update_at")
    private LocalDateTime updateAt;
}
