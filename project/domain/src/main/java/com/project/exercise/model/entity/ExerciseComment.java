package com.project.exercise.model.entity;

import com.project.auth.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_comment")
public class ExerciseComment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ExerciseUser exerciseUser;

    @ManyToOne
    private User commenter;

}
