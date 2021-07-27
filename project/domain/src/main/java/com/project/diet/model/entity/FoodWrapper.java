package com.project.diet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_wrapper")
public class FoodWrapper {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Food food;

    @ManyToOne
    private Meal meal;

    /**
     * n 인분
     */
    @Column
    private int size;

}
