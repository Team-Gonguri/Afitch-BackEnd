package com.project.exercise.model.entity;

import com.project.auth.model.entity.User;
import com.project.exercise.model.entity.enums.PublicScope;
import com.project.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_participation")
public class ExerciseParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private PublicScope scope;

    @Column(nullable = false)
    private int score = -1;

    @ManyToOne
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date createdAt;

    public void updateScore(int score) {
        this.score = score;
    }

    public void updateUrl(String url) {
        this.url = url;
    }

    public ExerciseParticipation(String url, PublicScope scope, Exercise exercise, User user) {
        this.url = url;
        this.scope = scope;
        this.exercise = exercise;
        this.user = user;
        this.createdAt = DateUtils.now();
    }
}
