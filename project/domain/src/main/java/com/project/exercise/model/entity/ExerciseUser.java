package com.project.exercise.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.auth.model.entity.User;
import com.project.exercise.model.entity.enums.PublicScope;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_users")
public class ExerciseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PublicScope scope;

    @Column(nullable = false)
    private int score;

    @ManyToOne
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date createdAt;
}