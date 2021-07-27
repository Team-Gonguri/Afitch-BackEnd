package com.project.exercise.model.entity;

import com.project.auth.model.entity.User;
import com.project.exercise.model.entity.enums.PublicScope;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercises_users")
public class ExerciseUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String url;

    @Enumerated(EnumType.STRING)
    private PublicScope scope;

    @Column
    private int score;

    @ManyToOne
    private User user;
}
