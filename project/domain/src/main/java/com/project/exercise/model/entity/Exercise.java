package com.project.exercise.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column
    private int start;

    @Column
    private int end;


}
