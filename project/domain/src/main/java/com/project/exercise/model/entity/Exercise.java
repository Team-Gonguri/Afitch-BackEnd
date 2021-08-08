package com.project.exercise.model.entity;

import com.project.exercise.model.entity.enums.ExerciseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise",indexes = @Index(name = "i_category",columnList = "category"))
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @Column
    @Enumerated(EnumType.STRING)
    private ExerciseCategory category;


}
