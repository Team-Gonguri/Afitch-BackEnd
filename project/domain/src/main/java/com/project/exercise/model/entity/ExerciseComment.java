package com.project.exercise.model.entity;

import com.project.auth.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_comment")
public class ExerciseComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExerciseUser exerciseUser;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String text;

    @Column
    private Date createdAt;

    public void updateComment(String text){
        this.text = text;
    }

}
