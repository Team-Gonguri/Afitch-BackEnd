package com.project.diet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "food_wrapper")
public class FoodWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public FoodWrapper(Food food, Meal meal, int size) {
        this.food = food;
        this.meal = meal;
        this.size = size;
    }
}
