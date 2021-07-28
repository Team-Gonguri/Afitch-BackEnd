package com.project.diet.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.auth.model.entity.User;
import com.project.diet.model.entity.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private MealType type;


    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.REMOVE)
    private List<FoodWrapper> foods;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date createdAt;
}
